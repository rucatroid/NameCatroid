package org.catroid.catroid.ai;

import org.catroid.catroid.content.Sprite;
import org.catroid.catroid.content.bricks.MoveNStepsBrick;
import org.catroid.catroid.content.bricks.TurnLeftBrick;
import org.catroid.catroid.content.bricks.WaitBrick;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AiBrickConverter {

    public static void applyToSprite(Sprite sprite, String jsonResponse) {
        try {
            JsonObject root = JsonParser.parseString(jsonResponse).getAsJsonObject();
            if (!root.has("actions")) return;

            JsonArray actions = root.getAsJsonArray("actions");

            for (JsonElement element : actions) {
                JsonObject action = element.getAsJsonObject();
                String type = action.get("type").getAsString();

                switch (type) {
                    case "move":
                        int steps = action.has("steps") ? action.get("steps").getAsInt() : 10;
                        sprite.getScriptList().get(0).addBrick(new MoveNStepsBrick(sprite, steps));
                        break;

                    case "turn_left":
                        int degrees = action.has("degrees") ? action.get("degrees").getAsInt() : 15;
                        sprite.getScriptList().get(0).addBrick(new TurnLeftBrick(sprite, degrees));
                        break;

                    case "wait":
                        double seconds = action.has("seconds") ? action.get("seconds").getAsDouble() : 1.0;
                        sprite.getScriptList().get(0).addBrick(new WaitBrick(sprite, (int) (seconds * 1000)));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
