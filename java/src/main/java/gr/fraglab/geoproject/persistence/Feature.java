package gr.fraglab.geoproject.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Feature {

    @Id
    private String id;
    private String category;
    private String code;
    private String name;
    private String description;

    public static Feature of(String[] values) {
        Feature feature = new Feature();
        String[] splits = values[0].split("\\.");
        feature.setId(values[0].trim());
        feature.setCategory(splits[0].trim());
        if (splits.length > 1) {
            feature.setCode(splits[1].trim());
        }
        feature.setName(values[1].trim());
        if (values.length > 2) {
            feature.setDescription(values[2].trim());
        }
        return feature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
