/* Cuneiform: A Functional Language for Large Scale Scientific Data Analysis
 *
 * Copyright 2016 Jörgen Brandt, Marc Bux, and Ulf Leser
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
grammar Cuneiform ;

// parser rules

script       : stat* EOF ;

stat         : query
             | assign
             | defun ;
       
query        : compoundexpr SEMICOLON ;

defun        : DEFTASK ID sign( LBRACE assign+ RBRACE | IN lang BODY );

sign         : LPAREN param+ COLON inparam* RPAREN ;

inparam      : param
             | LSQUAREBR name+ RSQUAREBR ;
          
param        : name
             | LTAG name RTAG ;
          
name         : ID( LPAREN STRING RPAREN | LPAREN FILE RPAREN )? ; 

assign       : ID+ EQ compoundexpr SEMICOLON ;

lang         : BASH
             | R
             | PYTHON ;
          
compoundexpr : NIL
             | expr+ ;
             
expr         : ID     # VarExpr
             | INTLIT # IntLitExpr
             | STRLIT # StrLitExpr
             | cnd    # CndExpr
             | app    # AppExpr ;
             
cnd          : IF compoundexpr THEN compoundexpr ELSE compoundexpr END ;

app          : ID LPAREN ( binding ( COMMA binding )* )? RPAREN ;

binding      : ID COLON compoundexpr ;

// scanner rules

BASH         : [Bb] 'ash' ;
COLON        : ':' ;
COMMA        : ',' ;
DEFTASK      : 'deftask' ;
ELSE         : 'else' ;
END          : 'end' ;
EQ           : '=' ;
FILE         : 'File' ;
IF           : 'if' ;
IN           : 'in' ;
LBRACE       : '{' ;
LPAREN       : '(' ;
LSQUAREBR    : '[' ;
LTAG         : '<' ;
NIL          : 'nil' ;
PYTHON       : [Pp] 'ython' ;
R            : [Rr] ;
RBRACE       : '}' ;
RPAREN       : ')' ;
RSQUAREBR    : ']' ;
RTAG         : '>' ;
SEMICOLON    : ';' ;
STRING       : 'String' ;
THEN         : 'then' ;

INTLIT       : '-'? ( [0-9] | [1-9][0-9]* ) ;
STRLIT       : '"' .*? '"' ;
ID           : [a-zA-Z][a-zA-Z0-9\.\-_]* ;
BODY         : LMMECB .*? RMMECB ;

COMMENT1     : ('#' | '%' | '//' ) .*? '\n' -> skip ;
COMMENT2     : '/*' .*? '*/' -> skip ;
WS           : [ \t\n\r] -> skip ;

fragment LMMECB  : '*{' ;
fragment RMMECB  : '}*' ;


