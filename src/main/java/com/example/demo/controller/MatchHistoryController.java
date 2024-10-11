import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

@RestController
public class MatchHistoryController {

    @GetMapping("/Client/getProductsMatchHistoryByPuuid")
    public JSONObject getProductsMatchHistoryByPuuid(@RequestParam int begIndex) {
        // 模拟数据
        JSONObject matchHistory = new JSONObject();
        matchHistory.put("accountId", 4113105012L);
        matchHistory.put("games", new JSONArray());

        // 模拟分页数据
        for (int i = begIndex; i < begIndex + 10; i++) {
            JSONObject game = new JSONObject();
            game.put("gameId", 9384544488L + i);
            game.put("gameMode", "CLASSIC");
            game.put("gameCreation", System.currentTimeMillis());
            game.put("teams", new JSONArray());

            // 模拟团队数据
            JSONObject team = new JSONObject();
            team.put("win", i % 2 == 0);
            game.getJSONArray("teams").put(team);

            matchHistory.getJSONArray("games").put(game);
        }

        return matchHistory;
    }
}