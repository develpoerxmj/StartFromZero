package com.xmj.startfromzero.annotation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author XiaoMengjie
 */
public class InjectUtils {

    /**
     * 实现了跳转页面时，对于上一个页面intent所带数据的自动初始化
     * @param activity
     */
    public static void inject(Activity activity){
        if (null == activity){
            return;
        }
        Bundle bundle = activity.getIntent().getExtras();
        //得到class
        Class<? extends Activity> activityClass = activity.getClass();
        //得到所有参数
        Field[] declaredFields = activityClass.getDeclaredFields();
        //遍历
        for (Field field : declaredFields) {
            //参数注解为AutoWrite
            if (field.isAnnotationPresent(AutoWrite.class)){
                //得到注解
                AutoWrite autoWrite = field.getAnnotation(AutoWrite.class);
                //得到注解值
                String annotationValue = autoWrite.value();
                //判断Key----根据自定义规则
                String key = TextUtils.isEmpty(annotationValue)? field.getName() : annotationValue;
                //得到key所存的值
                Object value = null;
                if (bundle.containsKey(key)){
                     value = activity.getIntent().getExtras().get(key);
                }
                if (value == null) {
                    continue;
                }
                //得到参数类型的父类
                Class<?> componentType = field.getType().getComponentType();
                if (field.getType().isArray() && Parcelable.class.isAssignableFrom(componentType)){
                    //如果类型为Parcelable类型的数组，需要特殊处理
                    Object[] objects = (Object[]) value;
                    value = Arrays.copyOf(objects, objects.length, (Class<? extends Object[]>)field.getType());
                }
                try {
                    field.setAccessible(true);
                    field.set(activity, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
