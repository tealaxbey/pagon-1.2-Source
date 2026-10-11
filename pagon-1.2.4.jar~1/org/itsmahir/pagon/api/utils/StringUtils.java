package org.itsmahir.pagon.api.utils;

import java.util.regex.Pattern;
import net.minecraft.class_1293;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import org.itsmahir.pagon.api.value.custom.Bind;
import org.jetbrains.annotations.Nullable;

public class StringUtils {
   private static final Pattern PATTERN_CONTROL_CODE = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

   public static String removeLastChar(String str) {
      String output = "";
      if (str != null && !str.isEmpty()) {
         output = str.substring(0, str.length() - 1);
      }

      return output;
   }

   public static <T> @Nullable T parseId(class_2378<T> registry, String name) {
      name = name.toLowerCase().trim();
      class_2960 id;
      if (name.contains(":")) {
         id = class_2960.method_60654(name);
      } else {
         id = class_2960.method_60655("minecraft", name);
      }

      return (T)(registry.method_10250(id) ? registry.method_10223(id) : null);
   }

   public static String getKeyName(int keyCode) {
      if (keyCode == -1) {
         return "NONE";
      } else {
         switch (keyCode) {
            case 32:
               return "SPACE";
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 40:
            case 41:
            case 42:
            case 43:
            case 58:
            case 60:
            case 62:
            case 63:
            case 64:
            case 94:
            case 95:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 238:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248:
            case 249:
            case 250:
            case 251:
            case 252:
            case 253:
            case 254:
            case 255:
            case 270:
            case 271:
            case 272:
            case 273:
            case 274:
            case 275:
            case 276:
            case 277:
            case 278:
            case 279:
            case 285:
            case 286:
            case 287:
            case 288:
            case 289:
            case 315:
            case 316:
            case 317:
            case 318:
            case 319:
            case 337:
            case 338:
            case 339:
            default:
               return "UNKNOWN";
            case 39:
               return "APOSTROPHE";
            case 44:
               return "COMMA";
            case 45:
               return "MINUS";
            case 46:
               return "PERIOD";
            case 47:
               return "SLASH";
            case 48:
               return "0";
            case 49:
               return "1";
            case 50:
               return "2";
            case 51:
               return "3";
            case 52:
               return "4";
            case 53:
               return "5";
            case 54:
               return "6";
            case 55:
               return "7";
            case 56:
               return "8";
            case 57:
               return "9";
            case 59:
               return "SEMICOLON";
            case 61:
               return "EQUAL";
            case 65:
               return "A";
            case 66:
               return "B";
            case 67:
               return "C";
            case 68:
               return "D";
            case 69:
               return "E";
            case 70:
               return "F";
            case 71:
               return "G";
            case 72:
               return "H";
            case 73:
               return "I";
            case 74:
               return "J";
            case 75:
               return "K";
            case 76:
               return "L";
            case 77:
               return "M";
            case 78:
               return "N";
            case 79:
               return "O";
            case 80:
               return "P";
            case 81:
               return "Q";
            case 82:
               return "R";
            case 83:
               return "S";
            case 84:
               return "T";
            case 85:
               return "U";
            case 86:
               return "V";
            case 87:
               return "W";
            case 88:
               return "X";
            case 89:
               return "Y";
            case 90:
               return "Z";
            case 91:
               return "LEFT_BRACKET";
            case 92:
               return "BACKSLASH";
            case 93:
               return "RIGHT_BRACKET";
            case 96:
               return "GRAVE_ACCENT";
            case 161:
               return "WORLD_1";
            case 162:
               return "WORLD_2";
            case 256:
               return "ESCAPE";
            case 257:
               return "ENTER";
            case 258:
               return "TAB";
            case 259:
               return "BACKSPACE";
            case 260:
               return "INSERT";
            case 261:
               return "DELETE";
            case 262:
               return "RIGHT";
            case 263:
               return "LEFT";
            case 264:
               return "DOWN";
            case 265:
               return "UP";
            case 266:
               return "PAGE_UP";
            case 267:
               return "PAGE_DOWN";
            case 268:
               return "HOME";
            case 269:
               return "END";
            case 280:
               return "CAPS_LOCK";
            case 281:
               return "SCROLL_LOCK";
            case 282:
               return "NUM_LOCK";
            case 283:
               return "PRINT_SCREEN";
            case 284:
               return "PAUSE";
            case 290:
               return "F1";
            case 291:
               return "F2";
            case 292:
               return "F3";
            case 293:
               return "F4";
            case 294:
               return "F5";
            case 295:
               return "F6";
            case 296:
               return "F7";
            case 297:
               return "F8";
            case 298:
               return "F9";
            case 299:
               return "F10";
            case 300:
               return "F11";
            case 301:
               return "F12";
            case 302:
               return "F13";
            case 303:
               return "F14";
            case 304:
               return "F15";
            case 305:
               return "F16";
            case 306:
               return "F17";
            case 307:
               return "F18";
            case 308:
               return "F19";
            case 309:
               return "F20";
            case 310:
               return "F21";
            case 311:
               return "F22";
            case 312:
               return "F23";
            case 313:
               return "F24";
            case 314:
               return "F25";
            case 320:
               return "KP_0";
            case 321:
               return "KP_1";
            case 322:
               return "KP_2";
            case 323:
               return "KP_3";
            case 324:
               return "KP_4";
            case 325:
               return "KP_5";
            case 326:
               return "KP_6";
            case 327:
               return "KP_7";
            case 328:
               return "KP_8";
            case 329:
               return "KP_9";
            case 330:
               return "KP_DECIMAL";
            case 331:
               return "KP_DIVIDE";
            case 332:
               return "KP_MULTIPLY";
            case 333:
               return "KP_SUBTRACT";
            case 334:
               return "KP_ADD";
            case 335:
               return "KP_ENTER";
            case 336:
               return "KP_EQUAL";
            case 340:
               return "LEFT_SHIFT";
            case 341:
               return "LEFT_CONTROL";
            case 342:
               return "LEFT_ALT";
            case 343:
               return "LEFT_SUPER";
            case 344:
               return "RIGHT_SHIFT";
            case 345:
               return "RIGHT_CONTROL";
            case 346:
               return "RIGHT_ALT";
            case 347:
               return "RIGHT_SUPER";
            case 348:
               return "MENU";
         }
      }
   }

   public static String getKeyName(int keyCode, Bind bind) {
      if (keyCode == -1) {
         return "NONE";
      } else {
         return bind.getIsMouse() ? "MOUSE_" + bind.getKey() : getKeyName(keyCode);
      }
   }

   public static int getKeyCode(String key) {
      if (key.equalsIgnoreCase("NONE")) {
         return -1;
      } else {
         for(int i = 32; i < 97; ++i) {
            if (key.equalsIgnoreCase(getKeyName(i))) {
               return i;
            }
         }

         for(int i = 256; i < 349; ++i) {
            if (key.equalsIgnoreCase(getKeyName(i))) {
               return i;
            }
         }

         for(int i = 1000; i < 1010; ++i) {
            if (key.equalsIgnoreCase(getKeyName(i))) {
               return i;
            }
         }

         return -1;
      }
   }

   public static String getCategoryIcon(String category) {
      switch (category) {
         case "Client" -> {
            return "❄";
         }
         case "Combat" -> {
            return "\ud83d\udde1";
         }
         case "Movement" -> {
            return "\ud83c\udf2b";
         }
         case "Player" -> {
            return "\ud83d\udc31";
         }
         case "Render" -> {
            return "\ud83d\udc41";
         }
         case "Misc" -> {
            return "⚙";
         }
         case "Hud" -> {
            return "\ud83d\udcbb";
         }
         default -> {
            return "?";
         }
      }
   }

   public static String getPotionDurationString(class_1293 instance, float durationFactor) {
      if (instance.method_48559()) {
         return "**:**";
      } else {
         int i = class_3532.method_15375((float)instance.method_5584() * durationFactor);
         return ticksToElapsedTime(i);
      }
   }

   public static String ticksToElapsedTime(int ticks) {
      int i = ticks / 20;
      int j = i / 60;
      i %= 60;
      return i < 10 ? j + ":0" + i : j + ":" + i;
   }

   public static String stripControlCodes(String text) {
      return PATTERN_CONTROL_CODE.matcher(text).replaceAll("");
   }
}
