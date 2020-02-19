package com.company;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static String API_KEY = "trnsl.1.1.20200215T040829Z.14cdc4320dc5b8ce.3d78afd098686417b4ebfbbd9ce2a77bae5f825e";
    final static String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";

    class Yandex {
        String[] text;
    }

    public static void main(String[] args) throws IOException {
        /*
        //System.setProperty("https.proxyHost","proxy.isu.ru");
        //System.setProperty("https.proxyPort","3128");
        URL url = new URL(API_URL + "?text=I+like+M%26M%27s&format=plain&lang=en-ru&key="+API_KEY);
        InputStream stream = (InputStream) url.getContent();
        Scanner in = new Scanner(stream);
        String s = in.nextLine();
        System.out.println(s);
         */
        Scanner sc = new Scanner(System.in);
        // Т.к яндекс автоматически узнает язык введённого текста достаточно указать язык перевода
        System.out.println("Выберите на какой язык перевод:");
        String to = sc.nextLine();
        System.out.println("Введите переводимое предложение:");
        StringBuilder s = new StringBuilder();
        while (true) {
            String res;
            res = sc.nextLine();
            if (res.equals("")) {
                s.delete(s.length() - 3, s.length()); //удаление последнего пробела (%20)
                break;
            }
            s.append(URLEncoder.encode(res)).append("%20");
        }
        String API_TEXT = s.toString();
        /*
        азербайджанский	az	малаялам	ml
        албанский	sq	мальтийский	mt
        амхарский	am	македонский	mk
        английский	en	маори	mi
        арабский	ar	маратхи	mr
        армянский	hy	марийский	mhr
        африкаанс	af	монгольский	mn
        баскский	eu	немецкий	de
        башкирский	ba	непальский	ne
        белорусский	be	норвежский	no
        бенгальский	bn	панджаби	pa
        бирманский	my	папьяменто	pap
        болгарский	bg	персидский	fa
        боснийский	bs	польский	pl
        валлийский	cy	португальский	pt
        венгерский	hu	румынский	ro
        вьетнамский	vi	русский	ru
        гаитянский (креольский)	ht	себуанский	ceb
        галисийский	gl	сербский	sr
        голландский	nl	сингальский	si
        горномарийский	mrj	словацкий	sk
        греческий	el	словенский	sl
        грузинский	ka	суахили	sw
        гуджарати	gu	сунданский	su
        датский	da	таджикский	tg
        иврит	he	тайский	th
        идиш	yi	тагальский	tl
        индонезийский	id	тамильский	ta
        ирландский	ga	татарский	tt
        итальянский	it	телугу	te
        исландский	is	турецкий	tr
        испанский	es	удмуртский	udm
        казахский	kk	узбекский	uz
        каннада	kn	украинский	uk
        каталанский	ca	урду	ur
        киргизский	ky	финский	fi
        китайский	zh	французский	fr
        корейский	ko	хинди	hi
        коса	xh	хорватский	hr
        кхмерский	km	чешский	cs
        лаосский	lo	шведский	sv
        латынь	la	шотландский	gd
        латышский	lv	эстонский	et
        литовский	lt	эсперанто	eo
        люксембургский	lb	яванский	jv
        малагасийский	mg	японский	ja
        малайский	ms
         */

        String res = "";
        try {
            URL url = new URL(API_URL + "?text=" + API_TEXT + "&format=plain&lang=" + to + "&key=" + API_KEY);
            URLConnection yc = url.openConnection();
            BufferedReader buffIn = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = buffIn.readLine()) != null)
                res = (inputLine);
            buffIn.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Yandex y = gson.fromJson(res, Yandex.class);
        System.out.println(Arrays.toString(y.text));
    }
}
