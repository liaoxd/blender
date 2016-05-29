package com.halfcigarette.dietitian.beans;

import android.graphics.Bitmap;

/**
 * Created by MOON on 5/27/2016.
 */
public class Food {
    /**
     * @param name      名称：中文
     * @param attribute 蔬果属性：0-谷薯类  1-蔬果类  2-鱼肉类  3-奶豆类
     * @param reliang   热量
     * @param tanshui   碳水化合物
     * @param danbai    蛋白质
     * @param zhifang   脂肪
     * @param dangu     胆固醇
     * @param yesuan    叶酸
     * @param yansuan   烟酸
     * @param xianwei   纤维素
     * @param huluobo   胡萝卜素
     * @param gai       钙
     * @param xin       锌
     * @param jia       钾
     * @param lin       磷
     * @param dian      碘
     * @param na        钠
     * @param mei       镁
     * @param xi        硒
     * @param tie       铁
     * @param tong      铜
     * @param meng      镁
     * @param weia      维生素a
     * @param weib      维生素b
     * @param weib2     维生素b2
     * @param weib12    维生素b12
     * @param weib6     维生素6
     * @param weic      维生素c
     * @param weie      维生素e
     * @param weid      维生素d
     * @param weik      维生素k
     */

    //蔬果名称
    private String name;
    //蔬果简介
    private String introduce;
    //蔬果照片
    private Bitmap foodPhoto;
    //蔬果属性：0-谷薯类  1-蔬果类  2-鱼肉类  3-奶豆类
    private Integer attribute;
    //具体重量！只在有数据时赋值
    private float weight;

    //蔬果详细信息
    //热量（大卡）/100g
    private Double reliang;
    //碳水化合物(克)
    private Double tanshui;
    //蛋白质(克)
    private Double danbai;
    //脂肪(克)
    private Double zhifang;
    //胆固醇(毫克)
    private Double dangu;
    //叶酸(微克)
    private Double yesuan;
    //烟酸(毫克)
    private Double yansuan;
    //泛酸
    private double fansuan;
    //生物素
    private double shengwusu;
    //胆碱
    private double danjian;
    //膳食纤维(克) 
    private Double xianwei;
    //胡萝卜素(微克)
    private Double huluobo;
    //钙(毫克)
    private Double gai;
    //锌(毫克)
    private Double xin;
    //钾(毫克)
    private Double jia;
    //磷(毫克)
    private Double lin;
    //碘(微克) 
    private Double dian;
    //钠(毫克)
    private Double na;
    //镁(毫克)
    private Double mei;
    //硒(微克)
    private Double xi;
    //铁(毫克) 
    private Double tie;
    //铜(毫克)
    private Double tong;
    //锰(毫克)
    private Double meng;
    //维生素A(微克)
    private Double weia;
    //维生素B1(毫克)
    private Double weib;
    //维生素B2(毫克)
    private Double weib2;
    //维生素B12(毫克)
    private Double weib12;
    //维生素B6(毫克)
    private Double weib6;
    //维生素C(毫克) 
    private Double weic;
    //维生素E(毫克)
    private Double weie;
    //维生素D（微克）
    private Double weid;
    //维生素K（微克）
    private Double weik;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Bitmap getFoodPhoto() {
        return foodPhoto;
    }

    public void setFoodPhoto(Bitmap foodPhoto) {
        this.foodPhoto = foodPhoto;
    }

    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Double getReliang() {
        return reliang;
    }

    public void setReliang(Double reliang) {
        this.reliang = reliang;
    }

    public Double getTanshui() {
        return tanshui;
    }

    public void setTanshui(Double tanshui) {
        this.tanshui = tanshui;
    }

    public Double getDanbai() {
        return danbai;
    }

    public void setDanbai(Double danbai) {
        this.danbai = danbai;
    }

    public Double getZhifang() {
        return zhifang;
    }

    public void setZhifang(Double zhifang) {
        this.zhifang = zhifang;
    }

    public Double getDangu() {
        return dangu;
    }

    public void setDangu(Double dangu) {
        this.dangu = dangu;
    }

    public Double getYesuan() {
        return yesuan;
    }

    public void setYesuan(Double yesuan) {
        this.yesuan = yesuan;
    }

    public Double getYansuan() {
        return yansuan;
    }

    public void setYansuan(Double yansuan) {
        this.yansuan = yansuan;
    }

    public double getFansuan() {
        return fansuan;
    }

    public void setFansuan(double fansuan) {
        this.fansuan = fansuan;
    }

    public double getShengwusu() {
        return shengwusu;
    }

    public void setShengwusu(double shengwusu) {
        this.shengwusu = shengwusu;
    }

    public double getDanjian() {
        return danjian;
    }

    public void setDanjian(double danjian) {
        this.danjian = danjian;
    }

    public Double getXianwei() {
        return xianwei;
    }

    public void setXianwei(Double xianwei) {
        this.xianwei = xianwei;
    }

    public Double getHuluobo() {
        return huluobo;
    }

    public void setHuluobo(Double huluobo) {
        this.huluobo = huluobo;
    }

    public Double getXin() {
        return xin;
    }

    public void setXin(Double xin) {
        this.xin = xin;
    }

    public Double getGai() {
        return gai;
    }

    public void setGai(Double gai) {
        this.gai = gai;
    }

    public Double getJia() {
        return jia;
    }

    public void setJia(Double jia) {
        this.jia = jia;
    }

    public Double getLin() {
        return lin;
    }

    public void setLin(Double lin) {
        this.lin = lin;
    }

    public Double getDian() {
        return dian;
    }

    public void setDian(Double dian) {
        this.dian = dian;
    }

    public Double getNa() {
        return na;
    }

    public void setNa(Double na) {
        this.na = na;
    }

    public Double getMei() {
        return mei;
    }

    public void setMei(Double mei) {
        this.mei = mei;
    }

    public Double getXi() {
        return xi;
    }

    public void setXi(Double xi) {
        this.xi = xi;
    }

    public Double getTie() {
        return tie;
    }

    public void setTie(Double tie) {
        this.tie = tie;
    }

    public Double getTong() {
        return tong;
    }

    public void setTong(Double tong) {
        this.tong = tong;
    }

    public Double getMeng() {
        return meng;
    }

    public void setMeng(Double meng) {
        this.meng = meng;
    }

    public Double getWeia() {
        return weia;
    }

    public void setWeia(Double weia) {
        this.weia = weia;
    }

    public Double getWeib() {
        return weib;
    }

    public void setWeib(Double weib) {
        this.weib = weib;
    }

    public Double getWeib2() {
        return weib2;
    }

    public void setWeib2(Double weib2) {
        this.weib2 = weib2;
    }

    public Double getWeib12() {
        return weib12;
    }

    public void setWeib12(Double weib12) {
        this.weib12 = weib12;
    }

    public Double getWeib6() {
        return weib6;
    }

    public void setWeib6(Double weib6) {
        this.weib6 = weib6;
    }

    public Double getWeic() {
        return weic;
    }

    public void setWeic(Double weic) {
        this.weic = weic;
    }

    public Double getWeie() {
        return weie;
    }

    public void setWeie(Double weie) {
        this.weie = weie;
    }

    public Double getWeid() {
        return weid;
    }

    public void setWeid(Double weid) {
        this.weid = weid;
    }

    public Double getWeik() {
        return weik;
    }

    public void setWeik(Double weik) {
        this.weik = weik;
    }
}
