package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCar(String model, Integer series) {
        TypedQuery<User> query = null;
        String hql = "from User where car.model = :model and car.series = :series";

        try (Session session = sessionFactory.openSession()) {
            query = session.createQuery(hql, User.class);
            query.setParameter("model", model).setParameter("series", series);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query.getResultList();
    }

}
