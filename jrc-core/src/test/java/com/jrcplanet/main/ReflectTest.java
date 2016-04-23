package com.jrcplanet.main;

import com.jrcplanet.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by rxb on 2016/4/23.
 */
public class ReflectTest {
    public static void main(String[] args) {
        User user = new User();
        Class c = user.getClass();
        try {
            Method m = c.getDeclaredMethod("getIconCls");
            if (m != null) {
                m.invoke(user);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
