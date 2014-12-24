package com.exadel.belarusattractions.dao.pointOfInterest;

import com.exadel.belarusattractions.entity.PointOfInterest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:04 PM, 2/21/13
 */

@Repository
public class PointOfInterestDaoImpl implements PointOfInterestDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PointOfInterest get(Long id) {
        Query query = getCurrentSession().createQuery(
                "from PointOfInterest as poi where poi.id = :id"
        );
        query.setParameter("id", id);
        return (PointOfInterest) query.uniqueResult();
    }

    @Override
    public PointOfInterest get(String code) {
        Query query = getCurrentSession().createQuery(
                "from PointOfInterest as poi where poi.code = :code"
        );
        query.setParameter("code", code);
        return (PointOfInterest) query.uniqueResult();
    }

    @Override
    public void update(PointOfInterest object) {
        getCurrentSession().update(object);
    }

    @Override
    public void save(PointOfInterest object) {
        getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public void delete(PointOfInterest object) {
        getCurrentSession().delete(object);
    }

    @Override
    public void deleteById(Long id) {
        PointOfInterest poi = get(id);
        delete(poi);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PointOfInterest> getList() {
        return (getCurrentSession().createQuery("from PointOfInterest poi")).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PointOfInterest> getList(String type) {
        Query query = getCurrentSession().createQuery("from PointOfInterest poi where type = :type");
        query.setParameter("type", type);
        return query.list();
    }

    @Override
    public int count() {
        Query query = getCurrentSession().createQuery(
                "select count(*) from PointOfInterest poi");

        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
