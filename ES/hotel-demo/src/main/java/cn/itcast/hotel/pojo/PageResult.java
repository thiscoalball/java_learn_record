package cn.itcast.hotel.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PageResult {
    private long total;
    private List<HotelDoc> hotels;
}
