package com.halfcigarette.dietitian.data;

import com.halfcigarette.dietitian.beans.Cook;
import com.halfcigarette.dietitian.beans.Families;
import com.halfcigarette.dietitian.beans.NutritionElement;
import com.halfcigarette.dietitian.beans.People;
import com.halfcigarette.dietitian.beans.Tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dongweihang on 2015/11/9.
 */
public class StaticData {

    //存储用户添加的食物信息，包含重量和具体营养信息
    public static List<Tags.Food> foodList = new ArrayList<>();

    //存储首页推荐食物信息
    public static List<Tags.Food> foodHorizontalList =
            new ArrayList<>();

    //存储记录页面显示的大类营养信息
    public static List<NutritionElement> nutritionList =
            new ArrayList<>();

    //存储当前所有食物的营养总和
    public static HashMap<String, Double> nutritionHashMap =
            new HashMap<>();

    //存储食物营养摄入标准
    public static HashMap<String, HashMap<String, Double>> nutritionStandardHashMap =
            new HashMap<>();

    //存储数据库食物信息
    public static HashMap<String, Tags.Food> foodHashMap =
            new HashMap<>();

    //存储数据库食物图片资源id
    public static HashMap<String, Integer> foodImageSource =
            new HashMap<>();

    //存储首页的一些菜的信息
    public static List<Cook> cookList = new ArrayList<>();

    //存储用餐人信息
    public static List<People> peopleList = new ArrayList<>();

    //存储Families信息
    public static List<Families> families = new ArrayList<>();

    //用户添加的食物的总卡路里
    public static int allCalorie = 0;


    public static boolean IsNoFood() {
        return foodList.size() == 0;
    }

    public static boolean IsNoPeople() {
        return peopleList.size() == 0;
    }

    public static double getstd_reliang() {
        if (StaticData.IsNoPeople()) {
            return 1;
        }
        double std = 0;
        for (People p : StaticData.peopleList) {
            std += StaticData.getStd_reliang(p);
        }
        return std;
    }

    public static double getstd_danbai() {
        if (StaticData.IsNoPeople()) {
            return 1;
        }
        double std = 0;
        for (People p : StaticData.peopleList) {
            std += StaticData.getStd_danbai(p);
        }
        return std;
    }

    public static double getstd_zhifang() {
        if (StaticData.IsNoPeople()) {
            return 1;
        }
        double std = 0;
        for (People p : StaticData.peopleList) {
            std += StaticData.getStd_zhifang(p);
        }
        return std;
    }

    public static double getstd_wei() {
        if (StaticData.IsNoPeople()) {
            return 1;
        }
        double std = 0;
        for (People p : StaticData.peopleList) {
            std += StaticData.getStd_weia(p) + StaticData.getStd_weib(p) + StaticData.getStd_weib2(p) + StaticData.getStd_weic(p) + StaticData.getStd_weie(p);
        }
        return std*0.5;
    }

    public static double getstd_kuang() {
        if (StaticData.IsNoPeople()) {
            return 1;
        }
        double std = 0;
        for (People p : StaticData.peopleList) {
            std += StaticData.getStd_gai(p) + StaticData.getStd_xin(p) + StaticData.getStd_jia(p) + StaticData.getStd_lin(p)
                    + StaticData.getStd_dian(p) + StaticData.getStd_mei(p) + StaticData.getStd_xi(p) + StaticData.getStd_tie(p) + StaticData.getStd_tong(p);
        }
        return std *2.5;
    }

    public static double getstd_xianwei() {
        if (StaticData.IsNoPeople()) {
            return 1;
        }
        double std = 0;
        for (People p : StaticData.peopleList) {
            std += StaticData.getStd_xianwei(p);
        }
        return std*1.3 ;
    }

    public static double getfood_reliang() {
        double actual = 0;
        for (Tags.Food food : StaticData.foodList) {
            actual += food.getReliang();
        }
        return actual;
    }

    public static double getfood_danbai() {
        double actual = 0;
        for (Tags.Food food : StaticData.foodList) {
            actual += food.getDanbai();
        }
        return actual;
    }

    public static double getfood_zhifang() {
        double actual = 0;
        for (Tags.Food food : StaticData.foodList) {
            actual += food.getZhifang();
        }
        return actual;
    }

    public static double getfood_wei() {
        double actual = 0;
        for (Tags.Food food : StaticData.foodList) {
            actual += food.getWeia() + food.getWeib() + food.getWeib2() + food.getWeic() + food.getWeie();
        }
        return actual;
    }

    public static double getfood_kuang() {
        double actual = 0;
        for (Tags.Food food : StaticData.foodList) {
            actual += food.getGai() + food.getXin() + food.getJia() + food.getLin() + food.getDian()
                    + food.getMei() + food.getXi() + food.getTie() + food.getTong();
        }
        return actual;
    }

    public static double getfood_xianwei() {
        double actual = 0;
        for (Tags.Food food : StaticData.foodList) {
            actual += food.getXianwei();
        }
        return actual;
    }


    public static Double getStd_reliang(People p) {
        return (p.getWeight() * 9.0 * 2.0) / 3.0;
    }

    public static Double getStd_danbai(People p) {
        double result = nutritionStandardHashMap.get("danbai").get("recommend");

        if (p.getAge() <= 1) {
            return 0.0;
        }
        if (p.getAge() <= 15 && p.getAge() >= 2) {
            result = 25 + 5 * p.getAge();
            return result / 3;
        }
        if (!p.isSex()) {
            result = result * 0.90 / 3;
        }
        return result / 3;
    }

    public static Double getStd_zhifang(People p) {
        double result = nutritionStandardHashMap.get("zhifang").get("recommend");

        if (p.getAge() <= 15) {
            result = (0.3 + p.getAge() * 0.04) * result;
        }

        return result / 3;
    }

    public static Double getStd_yansuan(People p) {
        double result = nutritionStandardHashMap.get("yansuan").get("recommend");

        if (p.getAge() <= 15) {
            result = (0.3 + p.getAge() * 0.04) * result;
        }

        return result / 3;
    }

    public static Double getStd_xianwei(People p) {
        double result = nutritionStandardHashMap.get("xianwei").get("recommend");

        if (p.getAge() <= 15) {
            result = (0.3 + p.getAge() * 0.04) * result;
        }

        return result / 3;
    }

    public static Double getStd_gai(People p) {
        double result = nutritionStandardHashMap.get("gai").get("recommend");

        if (9 < p.getAge() && p.getAge() <= 17) {
            result = result * 1.2;
        }
        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.9;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.6;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_xin(People p) {
        double result = nutritionStandardHashMap.get("xin").get("recommend");

        if (9 < p.getAge() && p.getAge() <= 17) {
            result = result * 1.3;
        }
        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.7;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.4;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_jia(People p) {
        double result = nutritionStandardHashMap.get("jia").get("recommend");

        if (9 < p.getAge() && p.getAge() <= 17) {
            result = result * 0.9;
        }
        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.7;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.4;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_lin(People p) {
        double result = nutritionStandardHashMap.get("lin").get("recommend");

        return result / 3;
    }

    public static Double getStd_dian(People p) {
        double result = nutritionStandardHashMap.get("dian").get("recommend");

        if (9 < p.getAge() && p.getAge() <= 17) {
            result = result * 0.9;
        }
        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.7;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.4;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_mei(People p) {
        double result = nutritionStandardHashMap.get("mei").get("recommend");

        if (9 < p.getAge() && p.getAge() <= 17) {
            result = result * 0.9;
        }
        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.7;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.4;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_xi(People p) {
        double result = nutritionStandardHashMap.get("xi").get("recommend");

        if (9 < p.getAge() && p.getAge() <= 17) {
            result = result * 0.9;
        }
        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.7;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.4;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_tie(People p) {
        double result = nutritionStandardHashMap.get("tie").get("recommend");

        if (9 < p.getAge() && p.getAge() <= 17) {
            result = result * 0.9;
        }
        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.7;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.4;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_tong(People p) {
        double result = nutritionStandardHashMap.get("tong").get("recommend");

        if (9 < p.getAge() && p.getAge() <= 17) {
            result = result * 0.9;
        }
        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.7;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.4;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_weia(People p) {
        double result = nutritionStandardHashMap.get("weia").get("recommend");

        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.9;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.7;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_weib(People p) {
        double result = nutritionStandardHashMap.get("weib").get("recommend");

        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.9;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.7;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_weib2(People p) {
        double result = nutritionStandardHashMap.get("weib2").get("recommend");

        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.9;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.7;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_weic(People p) {
        double result = nutritionStandardHashMap.get("weic").get("recommend");

        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.9;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.7;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }

    public static Double getStd_weie(People p) {
        double result = nutritionStandardHashMap.get("weie").get("recommend");

        if (4 < p.getAge() && p.getAge() <= 9) {
            result = result * 0.9;
        }
        if (1 < p.getAge() && p.getAge() <= 4) {
            result = result * 0.7;
        }
        if (p.getAge() <= 1) {
            result = 0;
        }

        return result / 3;
    }


}
