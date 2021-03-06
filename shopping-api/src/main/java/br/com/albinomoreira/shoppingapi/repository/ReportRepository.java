package br.com.albinomoreira.shoppingapi.repository;

import br.com.albinomoreira.shoppingapi.model.Shop;
import br.com.albinomoreira.shoppingclient.dto.ShopReportDTO;

import java.util.Date;
import java.util.List;

public interface ReportRepository {

    public List<Shop> getShopByFilters(
      Date dataInicio,
      Date dataFim,
      Float valorMinimo
    );

    public ShopReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim
    );
}
