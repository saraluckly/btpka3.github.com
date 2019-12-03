package me.test.first.spring.boot.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.cglib.beans.BeanMap;

import java.util.Date;

/**
 * @author dangqian.zll
 * @date 2019-08-21
 */
public class BeanMapTest {

    @Test
    public void test01() {
        Date now = new Date();
        MyPojo bean = new MyPojo();
        bean.setName("zhang3");
        bean.setBirthday(now);
        bean.setMale(true);
        bean.setAge(11);

        BeanMap beanMap = BeanMap.create(bean);
        Assert.assertEquals(4, beanMap.size());
        Assert.assertSame("zhang3", beanMap.get("name"));
        Assert.assertSame(now, beanMap.get("birthday"));
        Assert.assertTrue((Boolean) beanMap.get("male"));
        Assert.assertEquals(11, beanMap.get("age"));

        // 通过Map 来修改 Bean
        beanMap.put("name", "li4");
        Assert.assertSame("li4", bean.getName());


    }

    public static class MyPojo {
        private String name;
        private Date birthday;
        private Boolean male;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public Boolean getMale() {
            return male;
        }

        public void setMale(Boolean male) {
            this.male = male;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
