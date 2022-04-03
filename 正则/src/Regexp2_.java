import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp2_ {
    public static void main(String[] args) {
        String content = "{\"pure_title\": \"外媒:中国三管齐下回应北约\",\"linkurl\": \"https%3A//www.baidu.com/s%3Fcl%3D3%26tn%3Dbaidutop10%26fr%3Dtop1000%26wd%3D%25E5%25A4%2596%25E5%25AA%2592%253A%25E4%25B8%25AD%25E5%259B%25BD%25E4%25B8%2589%25E7%25AE%25A1%25E9%25BD%2590%25E4%25B8%258B%25E5%259B%259E%25E5%25BA%2594%25E5%258C%2597%25E7%25BA%25A6%26rsv_idx%3D2%26rsv_dl%3Dfyb_n_homepage%26hisfilter%3D1\",\"views\": \"\",\"isViewed\": \"\",\"isNew\": \"\",\"heat_score\": \"2459590\",\"hotTags\": \"0\"},{\"pure_title\": \"全国失散时间最长的兄弟团圆\",\"linkurl\": \"https%3A//www.baidu.com/s%3Fcl%3D3%26tn%3Dbaidutop10%26fr%3Dtop1000%26wd%3D%25E5%2585%25A8%25E5%259B%25BD%25E5%25A4%25B1%25E6%2595%25A3%25E6%2597%25B6%25E9%2597%25B4%25E6%259C%2580%25E9%2595%25BF%25E7%259A%2584%25E5%2585%2584%25E5%25BC%259F%25E5%259B%25A2%25E5%259C%2586%26rsv_idx%3D2%26rsv_dl%3Dfyb_n_homepage%26hisfilter%3D1\",\"views\": \"\",\"isViewed\": \"\",\"isNew\": \"\",\"heat_score\": \"2336450\",\"hotTags\": \"0\"},{\"pure_title\": \"离群公象进村在屋檐下呼呼大睡\",\"linkurl\": \"https%3A//www.baidu.com/s%3Fcl%3D3%26tn%3Dbaidutop10%26fr%3Dtop1000%26wd%3D%25E7%25A6%25BB%25E7%25BE%25A4%25E5%2585%25AC%25E8%25B1%25A1%25E8%25BF%259B%25E6%259D%2591%25E5%259C%25A8%25E5%25B1%258B%25E6%25AA%2590%25E4%25B8%258B%25E5%2591%25BC%25E5%2591%25BC%25E5%25A4%25A7%25E7%259D%25A1%26rsv_idx%3D2%26rsv_dl%3Dfyb_n_homepage%26hisfilter%3D1\",\"views\": \"\",\"isViewed\": \"\",\"isNew\": \"\",\"heat_score\": \"2275880\",\"hotTags\": \"0\"},{\"pure_title\": \"央视曝致幻毒品蘑菇伪装成巧克力\",\"linkurl\": \"https%3A//www.baidu.com/s%3Fcl%3D3%26tn%3Dbaidutop10%26fr%3Dtop1000%26wd%3D%25E5%25A4%25AE%25E8%25A7%2586%25E6%259B%259D%25E8%2587%25B4%25E5%25B9%25BB%25E6%25AF%2592%25E5%2593%2581%25E8%2598%2591%25E8%258F%2587%25E4%25BC%25AA%25E8%25A3%2585%25E6%2588%2590%25E5%25B7%25A7%25E5%2585%258B%25E5%258A%259B%26rsv_idx%3D2%26rsv_dl%3Dfyb_n_homepage%26hisfilter%3D1\",\"views\": \"\",\"isViewed\": \"\",\"isNew\": \"\",\"heat_score\": \"2124583\",\"hotTags\": \"0\"},{\"pure_title\": \"日本总人口首次跌出世界前十\",\"linkurl\": ";

        Pattern compile = Pattern.compile("\"pure_title\": \"(\\S*)\",\"linkurl\"");
        Matcher matcher = compile.matcher(content);

        while (matcher.find()){
            System.out.println("找到：" + matcher.group(1));
        }
    }
}
