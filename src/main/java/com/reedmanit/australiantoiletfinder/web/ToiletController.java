package com.reedmanit.australiantoiletfinder.web;

import com.reedmanit.australiantoiletfinder.data.Feature;
import com.reedmanit.australiantoiletfinder.data.Toilet;
import com.reedmanit.australiantoiletfinder.service.ToiletRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@Controller
@RequestMapping("/toilets")


public class ToiletController {
    private final ToiletRepository toiletRepository;

    public ToiletController(ToiletRepository toiletRepository) {
        this.toiletRepository = toiletRepository;
    }

    @GetMapping
    public String listToilets(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<Toilet> toiletPage = toiletRepository.findAll(pageable);
        model.addAttribute("toiletPage", toiletPage);
        model.addAttribute("pageable", pageable);
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
