/**
 * Write your info here
 *
 * @name Mohamed Tarek
 * @id 49-12309
 * @labNumber 20
 */

grammar Task8;

/**
 * This rule is to check your grammar using "ANTLR Preview"
 */
test: /* (Rule1 | Rule2 | ... | RuleN)+ */ EOF; //Replace the non-fragment lexer rules here

// Write all the necessary lexer rules and fragment lexer rules here

IF options{caseInsensitive=true;}:'if';
ELSE options{caseInsensitive=true;}:'else';
COMP: '>' | '<' | '>=' | '<=' | '==' | '!=';
ID:(LETTER | US) (LETTER | DIGITS | US)*;
LP: '(';
RP: ')';
NUM: DIGITS ((DOT DIGITS EXPO*)? | EXPO );
LIT: DQ ( '\\' . | ~('\\' | '"') )* DQ;

fragment LETTER:([a-z]|[A-Z]);
fragment DIGITS:[0-9]+;
fragment US:'_';
fragment DOT:'.';
fragment DQ:'"';
fragment EXPO:('e'|'E') ('+' | '-')? DIGITS;


WS:[ \r\t\n]+ -> skip;

