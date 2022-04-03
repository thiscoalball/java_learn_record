package cn.itcast.hotel.service.impl;

import cn.itcast.hotel.mapper.HotelMapper;
import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.pojo.PageResult;
import cn.itcast.hotel.pojo.RequestParams;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
    @Autowired
    private RestHighLevelClient client;

    @Override
    public PageResult search(RequestParams params) throws IOException {
        int size = params.getSize();
        int page = params.getPage();
        //1 准备Request
        SearchRequest request = new SearchRequest("hotel");
        //2 准备DSL
        //2.1 构建BooleanQuery
        buildBasicQuery(params, request);
        //分页
        request.source().from((page - 1) * size)
                        .size(size);
        //2.2 排序
        if(!StringUtils.isEmpty(params.getLocation())){
            request.source().sort(SortBuilders
                    .geoDistanceSort("location", new GeoPoint(params.getLocation()))
                    .order(SortOrder.ASC)
                    .unit(DistanceUnit.KILOMETERS));
        }
        //3 发送请求得到响应
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4 解析响应
        return handleResponse(response);
    }

    @Override
    public Map<String, List<String>> filters(RequestParams params) throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        buildBasicQuery(params,request);
        request.source().size(0);
        buildAggregation(request);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析结果
        Map<String, List<String>> result = new HashMap<>();
        Aggregations aggregations = response.getAggregations();
        // 4.1.根据品牌名称，获取品牌结果
        List<String> brandList = getAggByName(aggregations, "brandAgg");
        result.put("品牌", brandList);
        // 4.2.根据品牌名称，获取品牌结果
        List<String> cityList = getAggByName(aggregations, "cityAgg");
        result.put("城市", cityList);
        // 4.3.根据品牌名称，获取品牌结果
        List<String> starList = getAggByName(aggregations, "starAgg");
        result.put("星级", starList);

        return result;
    }

    @Override
    public List<String> getSuggestions(String key) throws IOException {
        List<String> suggestList = new ArrayList<>();
        SearchRequest request = new SearchRequest("hotel");
        request.source().suggest(new SuggestBuilder()
                .addSuggestion("suggestions",
                        SuggestBuilders
                                .completionSuggestion("suggestion")
                                .prefix(key)
                                .skipDuplicates(true)
                                .size(10)));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        CompletionSuggestion suggestions = response.getSuggest().getSuggestion("suggestions");
        List<CompletionSuggestion.Entry.Option> options = suggestions.getOptions();
        options.forEach(
                option -> {
                    suggestList.add(option.getText().toString());
                }
        );
        return suggestList;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        DeleteRequest request = new DeleteRequest("hotel",id.toString());
        client.delete(request,RequestOptions.DEFAULT);
    }

    @Override
    public void insertById(Long id) throws IOException {
        //这里其实不应该调用数据库的 而是远程调用服务 违反了微服务的初衷
        Hotel hotel = getById(id);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        IndexRequest request = new IndexRequest("hotel").id(id.toString());
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        client.index(request,RequestOptions.DEFAULT);
    }

    private List<String> getAggByName(Aggregations aggregations,String aggName) {
        // 4.1.根据聚合名称获取聚合结果
        Terms brandTerms = aggregations.get(aggName);
        // 4.2.获取buckets
        List<? extends Terms.Bucket> buckets = brandTerms.getBuckets();
        // 4.3.遍历
        List<String> brandList = new ArrayList<>();
        for (Terms.Bucket bucket : buckets) {
            // 4.4.获取key
            String key = bucket.getKeyAsString();
            brandList.add(key);
        }
        return brandList;
    }

    private void buildAggregation(SearchRequest request) {
        request.source().aggregation(AggregationBuilders
                .terms("brandAgg")
                .field("brand")
                .size(100)
        );
        request.source().aggregation(AggregationBuilders
                .terms("cityAgg")
                .field("city")
                .size(100)
        );
        request.source().aggregation(AggregationBuilders
                .terms("starAgg")
                .field("starName")
                .size(100)
        );
    }

    private void buildBasicQuery(RequestParams params, SearchRequest request) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //must部分 关键字搜索
        String key = params.getKey();
        if (key == null || "".equals(key)) {
            boolQuery.must(QueryBuilders.matchAllQuery());
        } else {
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        }
        //条件过滤部分
        if(!StringUtils.isEmpty(params.getCity())){
            boolQuery.filter(QueryBuilders.termQuery("city", params.getCity()));
        }
        if(!StringUtils.isEmpty(params.getBrand())){
            boolQuery.filter(QueryBuilders.termQuery("brand", params.getBrand()));
        }
        if(!StringUtils.isEmpty(params.getStarName())){
            boolQuery.filter(QueryBuilders.termQuery("starName", params.getStarName()));
        }
        //范围过滤
        if(params.getMinPrice()!=null || params.getMaxPrice()!=null){
            boolQuery.filter(QueryBuilders.rangeQuery("price").
                    gte(params.getMinPrice()).
                    lte(params.getMaxPrice()));
        }
        FunctionScoreQueryBuilder functionScoreQuery =
                QueryBuilders.functionScoreQuery(
                        // 原始查询，相关性算分的查询
                        boolQuery,
                        // function score的数组
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                                // 其中的一个function score 元素
                                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                        // 过滤条件
                                        QueryBuilders.termQuery("isAD", true),
                                        // 算分函数
                                        ScoreFunctionBuilders.weightFactorFunction(100)
                                )
                        });
        request.source().query(functionScoreQuery);
    }

    private PageResult handleResponse(SearchResponse response) {
        SearchHits hits = response.getHits();
        long value = hits.getTotalHits().value;

        List<HotelDoc> hotels = new ArrayList<>();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            String json = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            Object[] sortValues = hit.getSortValues();
            if(sortValues.length>0){
                Object sortValue = sortValues[0];
                hotelDoc.setDistance(sortValue);
            }
            hotels.add(hotelDoc);
        }
        return new PageResult(value,hotels);
    }
}
