package org.africalib.gallery.backend.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/Crawling")
public class WebCrawlerController {

    @GetMapping
    @ResponseBody // JSON 형식으로 변환해야 함
    public List<String> crawlData(Model model) { // (Model model)
        List<String> crawledData = new ArrayList<>();
        try {
            String url = "https://www.1365.go.kr/vols/1572247904127/partcptn/timeCptn.do";
            Document doc = Jsoup.connect(url).get();
            Elements h3Elements = doc.select("dt.txts,dt.tit_board_list");
            for (Element h3Element : h3Elements) {
                String title = h3Element.text();
                crawledData.add(title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crawledData;
    }
}
