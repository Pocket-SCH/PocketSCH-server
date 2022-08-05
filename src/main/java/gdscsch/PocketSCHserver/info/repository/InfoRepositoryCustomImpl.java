package gdscsch.PocketSCHserver.info.repository;

import gdscsch.PocketSCHserver.info.entity.Info;
import gdscsch.PocketSCHserver.info.entity.Keyword;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Component;


@Component
public class InfoRepositoryCustomImpl implements InfoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private String attachPercent(String likeKeyword) {
        return "%" + likeKeyword + "%";
    }

    @Override
    public List<Info> findInfoByTitles(List<Keyword> keywords) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Info> query = cb.createQuery(Info.class);
        Root<Info> info = query.from(Info.class);

        Path<String> infoPath = info.get("title");

        List<Predicate> predicates = new ArrayList<>();
        for (Keyword keyword : keywords) {
            predicates.add(cb.like(infoPath, attachPercent(keyword.getKeyword())));
        }
        query.select(info)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
            .getResultList();
    }
}