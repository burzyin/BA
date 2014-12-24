package com.exadel.belarusattractions.utils;

import com.exadel.belarusattractions.utils.json.JsonUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
* JsonUtils test.
* Tests its methods.
* <p/>
* Developer: Yan Khonskiy
* Created: 1:32 AM, 11/19/12
*/
public class JsonUtilsTest {

    public JsonUtilsTest() {
    }

    @Test
    public void testToJson() {
        Object o = new Object();
        String oJson = JsonUtils.toJson(o);

        Double d = 10D;
        String dJson = JsonUtils.toJson(d);

        List<TestModel> testModels = createTestModelsForTest1();
        String mJson = JsonUtils.toJson(testModels);

        assertNotNull(mJson);

        Object o1 = null;
        String o1Json = JsonUtils.toJson(o1);

        System.out.println(oJson);
        System.out.println(dJson);
        System.out.println(mJson);
        System.out.println(o1Json);

        assertNotNull(oJson);
        assertEquals(o1Json, "null");
    }

    @Test
    public void testToJsString() {
        Object o = new Object();
        String oJson = JsonUtils.toJsString(o);

        Double d = 10D;
        String dJson = JsonUtils.toJsString(d);

        List<TestModel> testModels = createTestModelsForTest1();
        String mJson = JsonUtils.toJsString(testModels);

        Object o1 = null;
        String o1Json = JsonUtils.toJsString(o1);

        System.out.println(oJson);
        System.out.println(dJson);
        System.out.println(mJson);
        System.out.println(o1Json);

        assertNotNull(o1Json);
        assertNotSame(o1Json, "\"\"");
    }

    private List<TestModel> createTestModelsForTest1() {
        List<TestModel> testModels = new ArrayList<TestModel>();

        List<String> ss1 = new ArrayList<String>();
        ss1.add("ss11");
        ss1.add("ss12");
        TestModel m1 = initModel(1L, new Date(11111111), "A1", ss1);

        List<String> ss2 = new ArrayList<String>();
        ss2.add("ss21");
        ss2.add("ss22");
        ss2.add("ss23");
        TestModel m2 = initModel(2L, new Date(22222222), "A2", ss2);

        TestModel m3 = initModel(3L, new Date(33333333), null, null);

        testModels.add(m1);
        testModels.add(m2);
        testModels.add(m3);
        return testModels;
    }

    private TestModel initModel(Long id, Date date, String a, List<String> ss) {
        TestModel testModel = new TestModel();
        testModel.setA(a);
        testModel.setDate(date);
        testModel.setId(id);
        testModel.setSs(ss);
        return testModel;
    }
}


/**
* Test class model for JsonUtilsTest.
* Objects of TestModel class will be used only in methods of JsonUtilsTest class.
*/
@SuppressWarnings("UnusedDeclaration")
class TestModel {

    private Long id;
    private Date date;
    private String a;
    private List<String> ss;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public List<String> getSs() {
        return ss;
    }

    public void setSs(List<String> ss) {
        this.ss = ss;
    }
}