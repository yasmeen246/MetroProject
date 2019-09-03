package com.example.jasmn.metroproject;

import java.util.ArrayList;
import java.util.Collections;

public class lines {
    public static ArrayList<String> stations = new ArrayList<>();
    public static   ArrayList<String> stationsline2 = new ArrayList<>();
    public static ArrayList<String> stationsline3 = new ArrayList<>();
    public static ArrayList<String> allstation = new ArrayList<>();
    public static ArrayList<Double>lon=new ArrayList<>();
    public static ArrayList<Double>lat=new ArrayList<>();
    public static ArrayList<Double>lon2=new ArrayList<>();
    public static ArrayList<Double>lat2=new ArrayList<>();
    public static ArrayList<Double>latall=new ArrayList<>();
    public static ArrayList<Double>lonall=new ArrayList<>();

    public static   void insert() {
    Collections.addAll(lat, 29.850159, 29.864292, 29.871130, 29.880634, 29.898172, 29.906101, 29.926178, 29.936644
            , 29.946861, 29.953275, 29.960584, 29.969804, 29.982062, 29.995527, 30.006203, 30.029298, 30.036979, 30.044558
            , 30.044558, 30.056948, 30.061080, 30.069019, 30.077275, 30.082066, 30.087246, 30.091238,
            30.097634, 30.105951, 30.113283, 30.120902, 30.131035, 30.131035, 30.152132, 30.163644);
    Collections.addAll(lon, 31.334574, 31.324439, 31.319284, 31.313914, 31.303572, 31.299239, 31.287560, 31.281397
            , 31.272986, 31.263030, 31.257516, 31.25085, 31.241862, 31.230897, 31.229127, 31.234911, 31.237851, 31.234289
            , 31.234289, 31.242039, 31.245816, 31.26436, 31.277541, 31.287208, 31.293838, 31.298612, 31.304320, 31.310081, 31.313461
            , 31.313440, 31.318504, 31.318504, 31.33533, 31.337993);

Collections.addAll(lon2,29.981056,29.995598,30.005481,30.010672,30.017452,30.026005,30.035886,30.038443,30.044557,30.045327,30.044730,30.045327,30.052352,30.061113,30.070901,30.080605,30.087966,30.097839,30.104069,30.113672,30.122422);
Collections.addAll(lat2,31.212309, 31.208462, 31.207906,31.20709, 31.203752, 31.201120, 31.200135, 31.212229, 31.212229, 31.234487, 31.235399, 31.244159, 31.246791, 31.246039, 31.245101, 31.245403,31.245497, 31.245008, 31.245584, 31.248753, 31.244489);
 latall.addAll(lat);
latall.addAll(lat2);
lonall.addAll(lon);
lonall.addAll(lon2);
    stations.add("--اختر محطة--");
    stations.add("--******الخط الاول******--");
    stations.add("حلوان");
    stations.add("عين حلوان");
    stations.add("جامعة حلوان");
    stations.add("وادى حوف");
    stations.add("حدائق حلوان");
    stations.add("المعصرة");
    stations.add("طرة الاسمنت");
    stations.add("كوتسيكا");
    stations.add("طرة البلد");
    stations.add("ثكنات المعادي");
    stations.add("المعادى");
    stations.add("حدائق المعادى");
    stations.add("دار السلام");
    stations.add("الزهراء");
    stations.add("مار جرجس");
    stations.add("الملك الصالح");
    stations.add("السيدة زينب");
    stations.add("سعد زغلول");
    stations.add("السادات");
    stations.add("جمال عبدالناصر");
    stations.add("عرابى ");
    stations.add("الشهداء");
    stations.add("غمرة");
    stations.add("الدمرداش ");
    stations.add("منشية الصدر");
    stations.add("كوبرى القبة ");
    stations.add("حمامات القبة ");
    stations.add("سراى القبة ");
    stations.add("حدائق الزيتون");
    stations.add("حلمية الزيتون ");
    stations.add(" المطرية");
    stations.add("عين شمس");
    stations.add("عزبة النخل");
    stations.add("المرج");
    stations.add("المرج الجديدة");

    Collections.addAll(stationsline2, "--******الخط الثانى******--", "المنيب", " ساقية مكي", " أم المصريين", "الجيزة", " فيصل", "جامعة القاهرة", " البحوث", " الدقي", " الأوبرا", "السادات", "التحرير", "محمد نجيب", "العتبة", "الشهداء", " مسرة", " روض الفرج", " سانت تريزا", " الخلفاوي", " المظلات", " كلية الزراعة", "شبرا الخيمة");
    Collections.addAll(stationsline3, "--******الخط الثالت******--", "مطار القاهرة", " الشهيد أحمد جلال", "السلام", "الحرفيين", "عمر بن الخطاب", "قباء", "النزهة2", "النزهة1", "نادى الشمس", "ألف مسكن", "ميدان هليوبليس", "هارون", "الأهرام", "كلية البنات", "الأستاد", "أرض المعارض", "العباسية", "عبده باشا", "الجيش", "باب الشعرية",
            "العتبة", "جمال عبدالناصر", "ماسبيرو", " الزمالك", "الكيت كات ", "السودان", "امبابة", "البوهى", "القومية العربية", " محطة الطريق الدائري", "محور روض الفرج", "التوفيقية", "وادي النيل", "جامعة الدول العربية", "بولاق الدكرور", "جامعة القاهرة");

    allstation.addAll(stations);
    allstation.addAll(stationsline2);
    allstation.addAll(stationsline3);


}}
