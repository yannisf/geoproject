package gr.fraglab.geoproject.persistence;

import gr.fraglab.geoproject.util.LanguageDetectionTool;
import gr.fraglab.geoproject.vo.LineRecord;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class GeoEntry {

    @Id()
    private String geonameid;
    private String name;
    private String asciiName;
    @ElementCollection
    private Set<AlternateName> alternateNames;
    private String latitude;
    private String longitude;
    private String featureClass;
    private String featureCode;
    private String countryCode;
    private String cc2;
    private String admin1Code;
    private String admin2Code;
    private String admin3Code;
    private String admin4Code;
    private String population;
    private String elevation;
    private String dem;
    private String timezone;
    private String modificationDate;

    public String getGeonameid() {
        return geonameid;
    }

    public void setGeonameid(String geonameid) {
        this.geonameid = geonameid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiName() {
        return asciiName;
    }

    public void setAsciiName(String asciiName) {
        this.asciiName = asciiName;
    }

    public Set<AlternateName> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(Set<AlternateName> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getFeatureClass() {
        return featureClass;
    }

    public void setFeatureClass(String featureClass) {
        this.featureClass = featureClass;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getAdmin1Code() {
        return admin1Code;
    }

    public void setAdmin1Code(String admin1Code) {
        this.admin1Code = admin1Code;
    }

    public String getAdmin2Code() {
        return admin2Code;
    }

    public void setAdmin2Code(String admin2Code) {
        this.admin2Code = admin2Code;
    }

    public String getAdmin3Code() {
        return admin3Code;
    }

    public void setAdmin3Code(String admin3Code) {
        this.admin3Code = admin3Code;
    }

    public String getAdmin4Code() {
        return admin4Code;
    }

    public void setAdmin4Code(String admin4Code) {
        this.admin4Code = admin4Code;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getDem() {
        return dem;
    }

    public void setDem(String dem) {
        this.dem = dem;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public static GeoEntry from(LineRecord lineRecord) {
        GeoEntry entry = new GeoEntry();
        BeanUtils.copyProperties(lineRecord, entry);
        if (StringUtils.isNotBlank(lineRecord.getAlternateNames())) {
            String[] splits = lineRecord.getAlternateNames().split(",");
            Set<AlternateName> alternateNameSet = Stream.of(splits)
                    .map(String::trim)
                    .map(n -> {
                        AlternateName alternateName = new AlternateName();
                        alternateName.setName(n);
                        alternateName.setScript(LanguageDetectionTool.detect(n));
                        return alternateName;
                    }).collect(Collectors.toSet());
            entry.setAlternateNames(alternateNameSet);
        }
        return entry;
    }

}
