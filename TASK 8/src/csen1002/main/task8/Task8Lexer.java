// Generated from D:/MOHMMED/Intellij Project/TASK8/grammers\Task8.g4 by ANTLR 4.12.0
package csen1002.main.task8;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Task8Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, COMP=3, ID=4, LP=5, RP=6, NUM=7, LIT=8, WS=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "COMP", "ID", "LP", "RP", "NUM", "LIT", "LETTER", "DIGITS", 
			"US", "DOT", "DQ", "EXPO", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "COMP", "ID", "LP", "RP", "NUM", "LIT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public Task8Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Task8.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\tv\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00021\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0003\u00035\b\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0005\u0003:\b\u0003\n\u0003\f\u0003=\t\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006G\b\u0006\n\u0006\f\u0006J\t\u0006\u0003"+
		"\u0006L\b\u0006\u0001\u0006\u0003\u0006O\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007U\b\u0007\n\u0007\f\u0007X\t\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0003\b]\b\b\u0001\t\u0004\t`\b\t\u000b"+
		"\t\f\ta\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0003\rl\b\r\u0001\r\u0001\r\u0001\u000e\u0004\u000eq\b\u000e"+
		"\u000b\u000e\f\u000er\u0001\u000e\u0001\u000e\u0000\u0000\u000f\u0001"+
		"\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007"+
		"\u000f\b\u0011\u0000\u0013\u0000\u0015\u0000\u0017\u0000\u0019\u0000\u001b"+
		"\u0000\u001d\t\u0001\u0000\u000b\u0002\u0000IIii\u0002\u0000FFff\u0002"+
		"\u0000EEee\u0002\u0000LLll\u0002\u0000SSss\u0002\u0000<<>>\u0002\u0000"+
		"\"\"\\\\\u0002\u0000AZaz\u0001\u000009\u0002\u0000++--\u0003\u0000\t\n"+
		"\r\r  \u007f\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000"+
		"\u0003\"\u0001\u0000\u0000\u0000\u00050\u0001\u0000\u0000\u0000\u0007"+
		"4\u0001\u0000\u0000\u0000\t>\u0001\u0000\u0000\u0000\u000b@\u0001\u0000"+
		"\u0000\u0000\rB\u0001\u0000\u0000\u0000\u000fP\u0001\u0000\u0000\u0000"+
		"\u0011\\\u0001\u0000\u0000\u0000\u0013_\u0001\u0000\u0000\u0000\u0015"+
		"c\u0001\u0000\u0000\u0000\u0017e\u0001\u0000\u0000\u0000\u0019g\u0001"+
		"\u0000\u0000\u0000\u001bi\u0001\u0000\u0000\u0000\u001dp\u0001\u0000\u0000"+
		"\u0000\u001f \u0007\u0000\u0000\u0000 !\u0007\u0001\u0000\u0000!\u0002"+
		"\u0001\u0000\u0000\u0000\"#\u0007\u0002\u0000\u0000#$\u0007\u0003\u0000"+
		"\u0000$%\u0007\u0004\u0000\u0000%&\u0007\u0002\u0000\u0000&\u0004\u0001"+
		"\u0000\u0000\u0000\'1\u0007\u0005\u0000\u0000()\u0005>\u0000\u0000)1\u0005"+
		"=\u0000\u0000*+\u0005<\u0000\u0000+1\u0005=\u0000\u0000,-\u0005=\u0000"+
		"\u0000-1\u0005=\u0000\u0000./\u0005!\u0000\u0000/1\u0005=\u0000\u0000"+
		"0\'\u0001\u0000\u0000\u00000(\u0001\u0000\u0000\u00000*\u0001\u0000\u0000"+
		"\u00000,\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u00001\u0006\u0001"+
		"\u0000\u0000\u000025\u0003\u0011\b\u000035\u0003\u0015\n\u000042\u0001"+
		"\u0000\u0000\u000043\u0001\u0000\u0000\u00005;\u0001\u0000\u0000\u0000"+
		"6:\u0003\u0011\b\u00007:\u0003\u0013\t\u00008:\u0003\u0015\n\u000096\u0001"+
		"\u0000\u0000\u000097\u0001\u0000\u0000\u000098\u0001\u0000\u0000\u0000"+
		":=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000"+
		"\u0000<\b\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>?\u0005(\u0000"+
		"\u0000?\n\u0001\u0000\u0000\u0000@A\u0005)\u0000\u0000A\f\u0001\u0000"+
		"\u0000\u0000BN\u0003\u0013\t\u0000CD\u0003\u0017\u000b\u0000DH\u0003\u0013"+
		"\t\u0000EG\u0003\u001b\r\u0000FE\u0001\u0000\u0000\u0000GJ\u0001\u0000"+
		"\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IL\u0001"+
		"\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KC\u0001\u0000\u0000\u0000"+
		"KL\u0001\u0000\u0000\u0000LO\u0001\u0000\u0000\u0000MO\u0003\u001b\r\u0000"+
		"NK\u0001\u0000\u0000\u0000NM\u0001\u0000\u0000\u0000O\u000e\u0001\u0000"+
		"\u0000\u0000PV\u0003\u0019\f\u0000QR\u0005\\\u0000\u0000RU\t\u0000\u0000"+
		"\u0000SU\b\u0006\u0000\u0000TQ\u0001\u0000\u0000\u0000TS\u0001\u0000\u0000"+
		"\u0000UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000"+
		"\u0000\u0000WY\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000YZ\u0003"+
		"\u0019\f\u0000Z\u0010\u0001\u0000\u0000\u0000[]\u0007\u0007\u0000\u0000"+
		"\\[\u0001\u0000\u0000\u0000]\u0012\u0001\u0000\u0000\u0000^`\u0007\b\u0000"+
		"\u0000_^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a_\u0001\u0000"+
		"\u0000\u0000ab\u0001\u0000\u0000\u0000b\u0014\u0001\u0000\u0000\u0000"+
		"cd\u0005_\u0000\u0000d\u0016\u0001\u0000\u0000\u0000ef\u0005.\u0000\u0000"+
		"f\u0018\u0001\u0000\u0000\u0000gh\u0005\"\u0000\u0000h\u001a\u0001\u0000"+
		"\u0000\u0000ik\u0007\u0002\u0000\u0000jl\u0007\t\u0000\u0000kj\u0001\u0000"+
		"\u0000\u0000kl\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0003"+
		"\u0013\t\u0000n\u001c\u0001\u0000\u0000\u0000oq\u0007\n\u0000\u0000po"+
		"\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000"+
		"\u0000rs\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0006\u000e"+
		"\u0000\u0000u\u001e\u0001\u0000\u0000\u0000\u000e\u0000049;HKNTV\\akr"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}