package br.com.palpitou.controller;


import br.com.palpitou.dto.Dashboard.BolaoDashboardResponse;
import br.com.palpitou.dto.Dashboard.DashboardResponse;
import br.com.palpitou.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DashboardResponse> getDashboard() {
        return ResponseEntity.ok(dashboardService.buscarDashboard());
    }

    @GetMapping("/boloes")
    @PreAuthorize("hasRole('ADMIN')")
    public List<BolaoDashboardResponse> buscarBoloesDashboard() {
        return dashboardService.buscarBoloesDashboard();
    }
}
