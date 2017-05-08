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
package ru.beykerykt.bullsandcows.implementation;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ru.beykerykt.bullsandcows.base.players.bot.finder.CodeFinder;
import ru.beykerykt.bullsandcows.base.players.bot.finder.RandomFinder;
import ru.beykerykt.bullsandcows.base.players.bot.finder.bob.BobFinder;
import ru.beykerykt.bullsandcows.base.utils.GameUtils;
import ru.beykerykt.bullsandcows.implementation.Resources.Localization;

public class GUi {

	private JFrame frame;

	// Fonts
	private GraphicsEnvironment environment;
	private Font fontBig;
	private Font fontRegular;
	private Font fontItalic;

	// Swing
	private JTextField usercode;
	private JTextField pName;
	private JTextField bName;
	private JComboBox<String> algorithms;
	private JButton start;
	private JLabel botCode;
	private JButton randomButton;
	private JLabel attempt;

	// GameBase
	private CodeFinder finder;
	private int guesses = 0;

	private boolean run = false;
	private ScheduledFuture<?> sf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUi window = new GUi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUi() {
		initialize();
	}

	/**
	 * Register custom fonts
	 */
	private void registerFonts() {
		try {
			fontRegular = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(Resources.ResourcePaths.REGULAR_FONT_PATH)).deriveFont(14f);
			environment.registerFont(fontRegular);

			fontItalic = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(Resources.ResourcePaths.ITALIC_FONT_PATH)).deriveFont(14f);
			environment.registerFont(fontItalic);

			fontBig = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(Resources.ResourcePaths.REGULAR_FONT_PATH)).deriveFont(60f);
			environment.registerFont(fontBig);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		if (environment == null) {
			environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		}

		// Register fonts
		registerFonts();

		// Frame
		frame = new JFrame();
		frame.setTitle(Localization.TITLE_NAME + " [" + Localization.STAGE_VERSION + "]");
		try {
			frame.setIconImage(ImageIO.read(getClass().getResourceAsStream(Resources.ResourcePaths.ICON_PATH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.getContentPane().setFont(fontRegular);
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//////////////////////////////////////////////////////////////////////
		//
		// Start: NOT AVAILABLE IN DEVELOP BRANCH
		//
		/////////////////////////////////////////////////////////////////////

		// Player name
		JLabel playerName = new JLabel(Resources.Localization.PLAYER_NAME);
		playerName.setFont(fontRegular);
		playerName.setBounds(10, 11, 170, 20);
		frame.getContentPane().add(playerName);

		pName = new JTextField();
		pName.setEnabled(false);
		pName.setEditable(false);
		pName.setToolTipText(Localization.UNAVAILABLE);
		pName.setFont(fontItalic);
		pName.setBounds(10, 42, 170, 20);
		pName.setColumns(10);
		pName.setText(Localization.PLAYER_DEFAULT_NAME);
		frame.getContentPane().add(pName);

		// Bot name
		JLabel botName = new JLabel(Resources.Localization.BOT_NAME);
		botName.setFont(fontRegular);
		botName.setBounds(10, 73, 170, 20);
		frame.getContentPane().add(botName);

		bName = new JTextField();
		bName.setEnabled(false);
		bName.setEditable(false);
		bName.setToolTipText(Localization.UNAVAILABLE);
		bName.setFont(fontItalic);
		bName.setBounds(10, 104, 170, 20);
		bName.setColumns(10);
		bName.setText(Localization.BOT_DEFAULT_NAME);
		frame.getContentPane().add(bName);

		//////////////////////////////////////////////////////////////////////
		//
		// End: NOT AVAILABLE IN DEVELOP BRANCH
		//
		/////////////////////////////////////////////////////////////////////

		// Usercode
		JLabel yourCode = new JLabel(Localization.YOUR_CODE);
		yourCode.setToolTipText(Localization.YOUR_CODE_DESCRPTION);
		yourCode.setFont(fontRegular);
		yourCode.setBounds(10, 135, 170, 20);
		frame.getContentPane().add(yourCode);

		usercode = new JTextField();
		usercode.setToolTipText(Localization.YOUR_CODE_DESCRPTION);
		usercode.setBounds(10, 166, 170, 20);
		usercode.setFont(fontItalic);
		usercode.setColumns(10);
		frame.getContentPane().add(usercode);

		// Algorithm's
		JLabel algorithm = new JLabel(Resources.Localization.Algoritms.ALGORITHM_FOR_SEARCH);
		algorithm.setFont(fontRegular);
		algorithm.setBounds(10, 197, 170, 20);
		frame.getContentPane().add(algorithm);

		algorithms = new JComboBox<String>();
		algorithms.setEditable(false);
		algorithms.setModel(new DefaultComboBoxModel(new String[] { Resources.Localization.Algoritms.RANDOM_ALGORITHM, Resources.Localization.Algoritms.BOB_ALGORITHM, Resources.Localization.Algoritms.MAGIC_ALGORITHM }));
		algorithms.setFont(fontItalic);
		algorithms.setBounds(10, 228, 170, 20);
		frame.getContentPane().add(algorithms);

		// Start button
		start = new JButton(Resources.Localization.START_SEARCHING);
		start.setFont(fontItalic);
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!run) {
					start();
				} else {
					stop();
				}
			}
		});
		start.setBounds(10, 310, 170, 50);
		frame.getContentPane().add(start);

		// Bot code
		botCode = new JLabel("0000");
		botCode.setHorizontalAlignment(SwingConstants.CENTER);
		botCode.setFont(fontBig);
		botCode.setBounds(261, 42, 220, 89);
		frame.getContentPane().add(botCode);

		// I think, it's...
		JLabel ithink = new JLabel(Resources.Localization.I_THINK);
		ithink.setHorizontalAlignment(SwingConstants.CENTER);
		ithink.setFont(fontRegular);
		ithink.setBounds(261, 40, 220, 20);
		frame.getContentPane().add(ithink);

		// Attempts
		attempt = new JLabel(Resources.Localization.ATTEMPT + guesses);
		attempt.setHorizontalAlignment(SwingConstants.CENTER);
		attempt.setFont(fontRegular);
		attempt.setBounds(261, 140, 220, 20);
		frame.getContentPane().add(attempt);

		// Get Random Code
		randomButton = new JButton(Resources.Localization.RANDOM_BUTTON);
		randomButton.setFont(fontItalic);
		randomButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (randomButton.isEnabled()) {
					usercode.setText(GameUtils.generateRandomCode());
				}
			}
		});
		randomButton.setBounds(10, 269, 170, 30);
		frame.getContentPane().add(randomButton);
	}

	/**
	 * Stop
	 */
	public void stop() {
		// Enable random button
		randomButton.setEnabled(true);

		// Enable usercode
		usercode.setEnabled(true);
		usercode.setEditable(true);

		// Enable algorithms list
		algorithms.setEnabled(true);
		algorithms.setEditable(true);

		// Shutdown
		run = false;
		sf.cancel(false);
		GameUtils.shutdownExecutor(true);
		start.setText(Resources.Localization.START_SEARCHING);
	}

	/**
	 * Start
	 */
	public void start() {
		// Disable random button
		randomButton.setEnabled(false);

		// Disable usercode
		usercode.setEnabled(false);
		usercode.setEditable(false);

		// Disable algorithms list
		algorithms.setEnabled(false);
		algorithms.setEditable(false);

		// Checking
		try {
			Integer.valueOf(usercode.getText());
		} catch (Exception ex) {
			// Set null
			usercode.setText(null);
		}

		if (usercode.getText().isEmpty() || usercode.getText().length() > 4) {
			usercode.setText(GameUtils.generateRandomCode()); // set random code
		}

		// Start searching
		start.setText(Resources.Localization.STOP_SEARCHING);
		if (algorithms.getSelectedItem().equals(Resources.Localization.Algoritms.RANDOM_ALGORITHM)) {
			finder = new RandomFinder();
		} else if (algorithms.getSelectedItem().equals(Resources.Localization.Algoritms.BOB_ALGORITHM)) {
			finder = new BobFinder();
		} else if (algorithms.getSelectedItem().equals(Resources.Localization.Algoritms.MAGIC_ALGORITHM)) {
			// Fast implementation for Magic algorithm
			finder = new CodeFinder() {

				@Override
				public void reset() {
				}

				@Override
				public void onReceivingResponse(String response) {
				}

				@Override
				public String getGuessCode() {
					return usercode.getText();
				}
			};
		}
		// Reset fields
		guesses = 0;
		finder.reset();

		// Start thread
		GameUtils.shutdownExecutor(false);
		sf = GameUtils.getExecutorService().scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				String guess = finder.getGuessCode();
				botCode.setText(guess);
				guesses++;
				attempt.setText(Resources.Localization.ATTEMPT + guesses);
				String response = getHint(guess);
				if (response.equals("WON")) {
					stop();
				}
			}
		}, 0, 5, TimeUnit.MILLISECONDS);
		run = true;
	}

	public String getHint(String guessFromAnotherPlayer) {
		int bulls = 0;
		int cows = 0;

		if (guessFromAnotherPlayer.length() < GameUtils.CODE_LENGTH) {
			return bulls + ":" + cows;
		}

		for (int i = 0; i < usercode.getText().length(); i++) {
			if (guessFromAnotherPlayer.charAt(i) == usercode.getText().charAt(i)) {
				bulls++;
			} else if (usercode.getText().contains(guessFromAnotherPlayer.charAt(i) + "")) {
				cows++;
			}
		}
		if (bulls == usercode.getText().length()) {
			return "WON";
		}
		// System.out.println(cows + " Cows and " + bulls + " Bulls.");
		return bulls + ":" + cows;
	}
}
