import javax.swing.JOptionPane;
public class HangmanGame {
	private String word;
	private char[] letters;
	private int lives; // # Of lives
	private char[] progress; // Char array of current progress
	private boolean gameOver = false; // boolean of if game is over

	public String getWord() {
		return word;
	}
	
	// Gets random word from list
	public String randomWord() {
		String[] arr = "abolish abridge abstract acclaim accommodate accord adamant advent adversary adversity aesthetic affiliation affirmation affliction aftermath aggravation ambidextrous ambiguous anecdote anguish animated annihilate anonymous apprehensive apprentice aptitude arbitrary arid artifact aspiration assent assertive audacious augment auxiliary avert belligerent benevolent benign bestow bewilder boisterous breach brevity calligraphy cavernous chaotic charisma choreography chronicle chronological circumstantial clientele coalition coerce collaborate collateral colossal commandeer commemorate communal competent concede concentric concise concurrent condemnation condole conglomerate conjecture conscientious consensus constrain contemplate contemporary contrary convene conventional convergent cordial cornucopia credible cynical decipher deficit denounce deprivation derivative designation desolate despicable deterrent deviate dilemma dire discern discrepancy disentangle dismantle dispel disposition dissension distortion divergent diversion dominion dormant dreary dubious eccentric eclipse ecstatic egotistical eloquent embark emphatic encompass eradicate erratic excise exclusion excruciating excursion exemplify expansive facilitate fanaticism fidelity flaunt fluctuate formative formulate fortification fossilized frivolous fruitless futile generic genre gingerly gratification gullible harmonious havoc hindrance homage homogeneous hypocritical hypothetical illustrious impediment impending impenetrable imperative implicit imposition impoverished improvise impulsive inanimate inaugural incarcerate incompatible inconceivable inconclusive inconsequential indispensable induce ineffectual inference infiltrate ingenuity ingrained inherent inhibit initiate innovation instigate integral interim intricate introvert irrelevant jargon jeopardy judicious kinetic laborious lattice lavish longevity luminous luxuriant malice malignant mediocre melancholy memorandum metaphor metropolitan moderation monotonous monumental morale naive nocturnal norm notorious novice nurture objective obliterate ominous omnipotent omniscient parable paradox paramount paraphrase patronize perennial perilous periphery pervasive piety placid plight porous posterity potency preamble predecessor predicament predominant procrastinate procure prophetic protocol proximity purge quarantine ransack ratification rationale ravage ravenous rectify referendum refrain refurbish regime rehabilitate reimburse relinquish reminiscent remnant renovate repercussion replenish repulsive requisite resolve retaliate retort reversion rhetoric rubble rustic sabotage saga sanction satire seasoned secular sedentary senile serene sinister skeptic sovereign spatial squander stagnant stature statutory stereotype strife stupendous subdue subterranean subtle subversive succumb superficial superlative supplement surge susceptible synthesis temperance tenacious tentative terrestrial torrential tranquil traverse trifle trivial turmoil unanimous undermine unison unprecedented unquenchable unrestrained unsightly unsurpassed vengeful versatile viable vibrant vicious vile vintage vogue wary wholesome"
				.split(" ");

		int wordIndex = (int) (arr.length * Math.random());
		return arr[wordIndex];
	}

	// Sets up hangman game
	HangmanGame(HangmanGui hangmanGui, int startLives) {
		lives = startLives;
		if (hangmanGui.getNumPlayers() == 2) {
			Object prompt = "Enter secret word: ";
			word = JOptionPane.showInputDialog(hangmanGui, prompt, "Hangman", JOptionPane.QUESTION_MESSAGE).toString().toLowerCase();
			}
		else {
			word = randomWord();
		}
		letters = word.toCharArray(); // Splits word to array of chars
		
		progress = createBlanks();
		hangmanGui.updateWordInProgress(progress);
		hangmanGui.showLivesLeft(lives);
	}

	// Creates char array of blanks the length of word
	public char[] createBlanks() {
		char[] blanks = new char[word.length()];
		for (int i = 0; word.length() > i; i++) {
			blanks[i] = '_';
		}
		return blanks;
	}


	// Check input
	public void check(char gLetter, HangmanGui hangmanGui) {
		// Check if letter is part of the word
		boolean notDone = true;
		for (int i = 0; word.length() > i; i++) {
			if (gLetter == letters[i]) {
				progress[i] = gLetter;
				notDone = false;// they guessed something right, so we might be done
			}
		}
		hangmanGui.updateWordInProgress(progress);
		// Check if game over in method complete()
		if (notDone) {
			lives -= 1;
			hangmanGui.showLivesLeft(lives);
		}
	}

	// Check amount of remaining blanks
	public int checkBlanks() {
		int blanksLeft = 0;
		for (int i = 0; word.length() > i; i++) {
			if ('_' == progress[i]) {
				blanksLeft += 1;
			}
		}
		return blanksLeft;
	}

	// Check why/if game over
	public void complete(HangmanGui hangmanGui) {
		if (lives == 0) {
			hangmanGui.youLose(word);
			setGameOver(true);
		} else if (checkBlanks() == 0) {
			hangmanGui.youWin();
			setGameOver(true);
		}
	}


	// Check if letter in word
	public void isGuessed(char g, HangmanGui hangmanGui) {
			check(g, hangmanGui);
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}