package com.jmss.infra.results;

import com.jmss.domain.Match;
import com.jmss.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO add logging
public final class OverallHtmlResult extends AbstractHtmlResult {

    public OverallHtmlResult(Match match) {
        // TODO move to some common constants area???
        super(match, "reports/overall.html");
    }

    // TODO add IOException to method signature
    @Override
    public String toHtml(boolean toPdf) {
        Map<Member, Double> results = getMatch().overall();
        List<CompetitorRecord> records = createRecords(results);

        // create results HTML string
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("match", getMatch());
        scopes.put("today", getTimestamp());
        scopes.put("records", records);

        return createHtmlResult(scopes);
    }

    @Override
    public String getInitialFileName() {
        return "overall";
    }
}
