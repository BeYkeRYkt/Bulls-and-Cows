/**
* MIT License
* 
* Copyright (c) 2017 Vladimir Mikhaylov
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
**/
package ru.beykerykt.bullsandcows;

import java.awt.Color;

public class Resources {

	public static class Localization {
		public static String TITLE_NAME = "Быки и коровы";
		public static String STAGE_VERSION = "Develop-0.0.1";

		public static String PLAYER_NAME = "Имя игрока:";
		public static String BOT_NAME = "Имя бота:";
		public static String PLAYER_DEFAULT_NAME = "DevelopedOne";
		public static String BOT_DEFAULT_NAME = "Bob";

		public static String YOUR_CODE = "Код для разгадывания:";
		public static String YOUR_CODE_DESCRPTION = "Ваш код должен содержать только 4 цифры от 0 до 6.";

		public static class Algoritms {
			public static String ALGORITHM_FOR_SEARCH = "Алгоритм для поиска:";
			public static String RANDOM_ALGORITHM = "Рандом";
			public static String BOB_ALGORITHM = "Алгоритм Боба Кули";
			public static String MAGIC_ALGORITHM = "Магический";
		}

		public static String START_SEARCHING = "Начать поиск";
		public static String STOP_SEARCHING = "Остановить поиск";
		public static String UNAVAILABLE = "Недоступно";

		public static String I_THINK = "Я думаю это...";
		public static String ATTEMPT = "Попытка номер ";
		public static String RANDOM_BUTTON = "Рандомное число";
	}

	public static class ResourcePaths {
		// Icons
		public static String ICON_PATH = "/res/icons/icon.png";

		// Fonts
		public static String REGULAR_FONT_PATH = "/res/fonts/Lato-Regular.ttf";
		public static String ITALIC_FONT_PATH = "/res/fonts/Lato-Italic.ttf";
	}

	public static class Colors {
		public static Color WHITE = Color.WHITE; // background ?
		public static Color CYAN = new Color(0, 177, 229); // cyanogen
	}
}
