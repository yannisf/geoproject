package gr.fraglab.geoproject.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexRepository extends ElasticsearchRepository<GeoEntry, String> {

}
