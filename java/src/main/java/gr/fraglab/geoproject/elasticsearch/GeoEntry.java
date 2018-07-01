package gr.fraglab.geoproject.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "geoproject", type = "geoentry")
public class GeoEntry {

    @Id
    private String id;
    private String name;
    private String adm1;
    private String adm2;
    private String adm3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdm1() {
        return adm1;
    }

    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }

    public String getAdm2() {
        return adm2;
    }

    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }

    public String getAdm3() {
        return adm3;
    }

    public void setAdm3(String adm3) {
        this.adm3 = adm3;
    }

}
