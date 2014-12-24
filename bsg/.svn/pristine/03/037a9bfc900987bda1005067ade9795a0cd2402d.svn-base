package com.exadel.belarusattractions.dao.sight;

import com.exadel.belarusattractions.dto.sights.Sight;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

/**
 * Developer: Paulau Aliaksandr
 * Created: 2:28 PM, 3/11/13
 */

@Repository
public class SightDaoImpl implements SightDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private final String baseHql = "select poi.id as id, poi.latitude as latitude, poi.longitude as longitude, poi.code as code, poi.pictogramUrl as pictogramUrl, content.longDescription as longDescription, content.shortDescription as shortDescription, content.name as name ";

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Sight getSightById(Long id, Locale locale) {
        Query query = getCurrentSession().createQuery(
                baseHql + "from PointOfInterest as poi " +
                        "join poi.content as content " +
                        "where content.locale = :locale and poi.id = :id");
        query.setParameter("id", id);
        query.setParameter("locale", locale.getLanguage());
        query.setResultTransformer(Transformers.aliasToBean(Sight.class));


        return (Sight) query.uniqueResult();

    }

    @Override
    @SuppressWarnings("unchecked")
    public Sight getSightByCode(String code, Locale locale) {
        Query query = getCurrentSession().createQuery(
                baseHql + "from PointOfInterest as poi join poi.photos as photos" +
                        " join poi.content as content " +
                        "where poi.code = :code and content.locale=:locale group by poi.id");

        query.setParameter("code", code);
        query.setParameter("locale", locale.getLanguage());
        query.setResultTransformer(Transformers.aliasToBean(Sight.class));

        return (Sight) query.uniqueResult();

//        List<Sight> sights = query.list();
//        return new Sight();
//        List<Photo> sight = (List<Photo>) getCurrentSession().createQuery(
//                "select poi.photos from PointOfInterest as poi where poi.code=:code")
//                .setParameter("code", code)
//                .list();
//        hqlBuilder.join("poi.content as content")
//                .where("content.locale = :locale and poi.code = :code");
//
//        String s = "select poi.id as id, poi.latitude as latitude, poi.longitude as longitude, poi.code as code, poi.pictogramUrl as pictogramUrl, content.longDescription as longDescription, content.shortDescription as shortDescription, content.name as name, poi.photos as photos from PointOfInterest as poi " +
//                "join poi.content as content " +
//                "where content.locale = :locale and poi.code = :code";
//        System.out.println( hqlBuilder.getHql());
//        Query query = getCurrentSession().createQuery(
//                hqlBuilder.getHql());
//        query.setParameter("code", code);
//        query.setParameter("locale", locale.getLanguage());
//        List list = query.list();
////        query.setResultTransformer(Transformers.aliasToBean(Sight.class));

//        Sight sight = (Sight) query.uniqueResult();

}

    @Override
    @SuppressWarnings("unchecked")
    public List<Sight> getSights(Locale locale) {
        Query query = getCurrentSession().createQuery(
                baseHql + "from PointOfInterest as poi " +
                        "join poi.content as content " +
                        "where content.locale = :locale");
        query.setParameter("locale", locale.getLanguage());
        query.setResultTransformer(Transformers.aliasToBean(Sight.class));

        List result = query.list();
        return (List<Sight>) result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sight> getSelectedSights(Long[] indices, Locale locale) {
        Query query = getCurrentSession().createQuery(
                baseHql + "from PointOfInterest as poi " +
                        "join poi.content as content " +
                        "where content.locale = :locale and poi.id in :indices");
        query.setParameterList("indices", indices);
        query.setParameter("locale", "en");
        query.setResultTransformer(Transformers.aliasToBean(Sight.class));

        List result = query.list();
        return (List<Sight>) result;
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
