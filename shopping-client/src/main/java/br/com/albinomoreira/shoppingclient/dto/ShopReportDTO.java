package br.com.albinomoreira.shoppingclient.dto;

import lombok.Data;

@Data
public class ShopReportDTO {
    private Integer count;
    private Double total;
    private Double mean;
}
