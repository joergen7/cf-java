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
 
grammar Effi ;

// parser rules

script     : LHASHBRACE( assoc( COMMA assoc )* )? RBRACE DOT EOF ;

assoc      : ARG RARROW map       # ArgAssoc
           | ID RARROW INTLIT     # IdAssoc
           | LAM RARROW lam       # LamAssoc
           | OUT RARROW binlst    # OutAssoc
           | RET RARROW map       # RetAssoc
           | TSTART RARROW INTLIT # TstartAssoc
           | TDUR RARROW INTLIT   # TdurAssoc
           | STATE RARROW OK      # StateAssoc
           ;

map        : LHASHBRACE( binding( COMMA binding )* )? RBRACE ;

binding    : STRLIT RARROW strlst ;

strlst     : LSQUAREBR( str( COMMA str )* )? RSQUAREBR ;

str        : LBRACE STR COMMA STRLIT RBRACE ;

lam        : LBRACE LAM COMMA INTLIT COMMA STRLIT COMMA sign COMMA forbody
             RBRACE ;

sign       : LBRACE SIGN COMMA neparamlst COMMA paramlst RBRACE ;

neparamlst : LSQUAREBR param( COMMA param )* RSQUAREBR ;
paramlst   : LSQUAREBR( param( COMMA param )* )? RSQUAREBR ;

param      : LBRACE PARAM COMMA LBRACE NAME COMMA STRLIT COMMA bool RBRACE
             COMMA bool RBRACE ;

forbody    : LBRACE FORBODY COMMA lang COMMA STRLIT RBRACE ;

binlst     : LSQUAREBR( bin( COMMA bin )* )? RSQUAREBR ;

bin        : DLTAG STRLIT? DRTAG ;

bool       : TRUE | FALSE ;

lang       : BASH | R | PYTHON ;

// scanner rules

ARG          : 'arg' ;
BASH         : 'bash' ;
COMMA        : ',' ;
DLTAG        : '<<' ;
DOT          : '.' ;
DRTAG        : '>>' ;
FALSE        : 'false' ;
FORBODY      : 'forbody' ;
ID           : 'id' ;
LAM          : 'lam' ;
LBRACE       : '{' ;
LHASHBRACE   : '#{' ;
LSQUAREBR    : '[' ;
NAME         : 'name' ;
OK           : 'ok' ;
OUT          : 'out' ;
PARAM        : 'param' ;
PYTHON       : 'python' ;
R            : 'r' ;
RARROW       : '=>' ;
RBRACE       : '}' ;
RET          : 'ret' ;
RSQUAREBR    : ']' ;
SIGN         : 'sign' ;
STATE        : 'state' ;
STR          : 'str' ;
TDUR         : 'tdur' ;
TRUE         : 'true' ;
TSTART       : 'tstart' ;


STRLIT       : '"' ( '\\"' | '\\\\' | '\\n' | . )*? '"' ;
INTLIT       : '0' | [1-9][0-9]* ;

COMMENT1     : '%' .*? '\n' -> skip ;
WS           : [ \t\n\r] -> skip ;

