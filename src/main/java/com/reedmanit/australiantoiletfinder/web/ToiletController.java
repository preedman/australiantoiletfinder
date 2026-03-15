package com.reedmanit.australiantoiletfinder.web;

import com.reedmanit.australiantoiletfinder.data.Feature;
import com.reedmanit.australiantoiletfinder.data.Toilet;
import com.reedmanit.australiantoiletfinder.service.ToiletRepository;
import com.reedmanit.australiantoiletfinder.service.ToiletSearchService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/toilets")


public class ToiletController {
    private final ToiletRepository toiletRepository;
    private final ToiletSearchService toiletSearchService;

    public ToiletController(ToiletRepository toiletRepository, ToiletSearchService toiletSearchService) {
        this.toiletRepository = toiletRepository;
        this.toiletSearchService = toiletSearchService;
    }

    @GetMapping
    public String listToilets(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<Toilet> toiletPage = toiletRepository.findAll(pageable);
        model.addAttribute("toiletPage", toiletPage);
        model.addAttribute("pageable", pageable);
        return "toilets/list";
    }

    @GetMapping("/nearby")
    public String findNearby(@RequestParam double lat,
                             @RequestParam double lon,
                             @RequestParam(defaultValue = "10.0") double radius,
                             @PageableDefault(size = 10) Pageable pageable,
                             Model model) {
        List<Toilet> nearbyToilets = toiletSearchService.findNearby(lon, lat, radius);
        
        // Manual pagination for the list
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), nearbyToilets.size());
        
        Page<Toilet> toiletPage;
        if (start <= nearbyToilets.size()) {
            toiletPage = new PageImpl<>(nearbyToilets.subList(start, end), pageable, nearbyToilets.size());
        } else {
            toiletPage = new PageImpl<>(List.of(), pageable, nearbyToilets.size());
        }
        
        model.addAttribute("toiletPage", toiletPage);
        model.addAttribute("pageable", pageable);
        model.addAttribute("lat", lat);
        model.addAttribute("lon", lon);
        
        return "toilets/list";
    }

    @Transactional(readOnly = true)
    @GetMapping("/{id}/features")
    public String toiletFeatures(@PathVariable Integer id, Model model) {
        Toilet toilet = toiletRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Toilet not found: " + id));

        // ... existing code ...

        // Pick the "best" feature record:
        // 1) prefer currently-active (endDate == null)
        // 2) then latest startDate
        // 3) then highest id
        Feature feature = toilet.getFeatures().stream()
                .max(Comparator
                        .comparing((Feature f) -> f.getEndDate() == null)          // active first
                        .thenComparing(Feature::getStartDate,
                                Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(Feature::getId,
                                Comparator.nullsLast(Comparator.naturalOrder()))
                )
                .orElse(null);

        model.addAttribute("toilet", toilet);
        model.addAttribute("feature", feature);
        return "toilets/features";
    }

    @GetMapping("/{id}/notes")
    public String toiletNotes(@PathVariable Integer id, Model model) {
        Toilet toilet = toiletRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Toilet not found: " + id));
        model.addAttribute("toilet", toilet);
        return "toilets/toiletnotes";
    }
}
