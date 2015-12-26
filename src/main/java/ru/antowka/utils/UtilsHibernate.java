package ru.antowka.utils;

import org.hibernate.criterion.Order;

/**
 * Created by Anton Nik on 26.12.15.
 */
public class UtilsHibernate {

    /**
     * Method create Order object for Hibernate Criteria by 2 Strings: order and field
     *
     * @param order
     * @param field
     * @return
     */
    public static Order getOrderByString(String order, String field){

        Order orderObj = null;

        switch (order){

            case "asc":

                orderObj = Order.asc(field);

                break;

            case "desc":

                orderObj = Order.desc(field);

                break;

            default:

                orderObj = Order.desc(field);

                break;
        }

        return orderObj;
    }
}
