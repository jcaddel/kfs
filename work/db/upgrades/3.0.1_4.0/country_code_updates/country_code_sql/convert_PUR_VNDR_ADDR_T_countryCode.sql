--
-- The Kuali Financial System, a comprehensive financial management system for higher education.
-- 
-- Copyright 2005-2014 The Kuali Foundation
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

-- This script will migrate a column in a table from the former Rice country 
-- codes which were based on FIPS 10-4 (with some minor differences) to the new
-- Rice country codes which are based on ISO 3166-1.  This script may not 
-- properly execute on columns with a primary key or unique constraint as some
-- of the former codes have merged (i.e. - all US Minor Outlying Islands have 
-- been unified under a single code, UM).  This script also does not take any 
-- action on codes that are not part of the list of former Rice country codes.
--
-- Table Name: 	PUR_VNDR_ADDR_T
-- Column Name: VNDR_CNTRY_CD
--
-- In order to avoid collisions between the former codes and the new codes, the 
-- codes are first changed to an interim, unique code.  Once that change is 
-- complete, they are changed to the new, correct code.
--
-- Change to temporary code
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='A0' where VNDR_CNTRY_CD='AA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='A1' where VNDR_CNTRY_CD='AC';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='A3' where VNDR_CNTRY_CD='AG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='A4' where VNDR_CNTRY_CD='AJ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='A7' where VNDR_CNTRY_CD='AN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='A9' where VNDR_CNTRY_CD='AQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='B1' where VNDR_CNTRY_CD='AS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='B2' where VNDR_CNTRY_CD='AT';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='B3' where VNDR_CNTRY_CD='AU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='B4' where VNDR_CNTRY_CD='AV';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='B5' where VNDR_CNTRY_CD='AY';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='B6' where VNDR_CNTRY_CD='BA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='B8' where VNDR_CNTRY_CD='BC';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='B9' where VNDR_CNTRY_CD='BD';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C1' where VNDR_CNTRY_CD='BF';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C2' where VNDR_CNTRY_CD='BG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C3' where VNDR_CNTRY_CD='BH';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C4' where VNDR_CNTRY_CD='BK';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C5' where VNDR_CNTRY_CD='BL';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C6' where VNDR_CNTRY_CD='BM';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C7' where VNDR_CNTRY_CD='BN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C8' where VNDR_CNTRY_CD='BO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='C9' where VNDR_CNTRY_CD='BP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='D0' where VNDR_CNTRY_CD='BQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='D2' where VNDR_CNTRY_CD='BS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='D4' where VNDR_CNTRY_CD='BU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='D6' where VNDR_CNTRY_CD='BX';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='D7' where VNDR_CNTRY_CD='BY';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='D9' where VNDR_CNTRY_CD='CB';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E0' where VNDR_CNTRY_CD='CD';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E1' where VNDR_CNTRY_CD='CE';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E2' where VNDR_CNTRY_CD='CF';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E3' where VNDR_CNTRY_CD='CG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E4' where VNDR_CNTRY_CD='CH';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E5' where VNDR_CNTRY_CD='CI';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E6' where VNDR_CNTRY_CD='CJ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E7' where VNDR_CNTRY_CD='CK';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='E9' where VNDR_CNTRY_CD='CN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='F1' where VNDR_CNTRY_CD='CQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='F2' where VNDR_CNTRY_CD='CR';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='F3' where VNDR_CNTRY_CD='CS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='F4' where VNDR_CNTRY_CD='CT';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='F7' where VNDR_CNTRY_CD='CW';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='F9' where VNDR_CNTRY_CD='CZ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='G0' where VNDR_CNTRY_CD='DA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='G2' where VNDR_CNTRY_CD='DO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='G3' where VNDR_CNTRY_CD='DR';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='G6' where VNDR_CNTRY_CD='EI';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='G7' where VNDR_CNTRY_CD='EK';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='G8' where VNDR_CNTRY_CD='EN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='H0' where VNDR_CNTRY_CD='ES';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='H2' where VNDR_CNTRY_CD='EU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='H3' where VNDR_CNTRY_CD='EZ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='H4' where VNDR_CNTRY_CD='FA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='H5' where VNDR_CNTRY_CD='FG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='I0' where VNDR_CNTRY_CD='FP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='I1' where VNDR_CNTRY_CD='FQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='I3' where VNDR_CNTRY_CD='FS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='I4' where VNDR_CNTRY_CD='GA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='I5' where VNDR_CNTRY_CD='GB';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='I6' where VNDR_CNTRY_CD='GE';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='I7' where VNDR_CNTRY_CD='GG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='J0' where VNDR_CNTRY_CD='GJ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='J1' where VNDR_CNTRY_CD='GK';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='J3' where VNDR_CNTRY_CD='GM';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='J4' where VNDR_CNTRY_CD='GO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='J6' where VNDR_CNTRY_CD='GQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='J9' where VNDR_CNTRY_CD='GV';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='K1' where VNDR_CNTRY_CD='GZ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='K2' where VNDR_CNTRY_CD='HA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='K5' where VNDR_CNTRY_CD='HO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='K6' where VNDR_CNTRY_CD='HQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='K9' where VNDR_CNTRY_CD='IC';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='L4' where VNDR_CNTRY_CD='IP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='L6' where VNDR_CNTRY_CD='IS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='L8' where VNDR_CNTRY_CD='IV';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='L9' where VNDR_CNTRY_CD='IY';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='M0' where VNDR_CNTRY_CD='IZ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='M1' where VNDR_CNTRY_CD='JA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='M4' where VNDR_CNTRY_CD='JN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='M6' where VNDR_CNTRY_CD='JQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='M7' where VNDR_CNTRY_CD='JU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='N0' where VNDR_CNTRY_CD='KN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='N1' where VNDR_CNTRY_CD='KQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='N2' where VNDR_CNTRY_CD='KR';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='N3' where VNDR_CNTRY_CD='KS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='N4' where VNDR_CNTRY_CD='KT';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='N5' where VNDR_CNTRY_CD='KU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='N8' where VNDR_CNTRY_CD='LE';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='N9' where VNDR_CNTRY_CD='LG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='O0' where VNDR_CNTRY_CD='LH';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='O1' where VNDR_CNTRY_CD='LI';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='O2' where VNDR_CNTRY_CD='LO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='O3' where VNDR_CNTRY_CD='LQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='O4' where VNDR_CNTRY_CD='LS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='O5' where VNDR_CNTRY_CD='LT';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='O8' where VNDR_CNTRY_CD='MA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='O9' where VNDR_CNTRY_CD='MB';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='P0' where VNDR_CNTRY_CD='MC';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='P2' where VNDR_CNTRY_CD='MF';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='P3' where VNDR_CNTRY_CD='MG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='P4' where VNDR_CNTRY_CD='MH';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='P5' where VNDR_CNTRY_CD='MI';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='P8' where VNDR_CNTRY_CD='MN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='P9' where VNDR_CNTRY_CD='MO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Q0' where VNDR_CNTRY_CD='MP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Q1' where VNDR_CNTRY_CD='MQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Q4' where VNDR_CNTRY_CD='MU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Q6' where VNDR_CNTRY_CD='MW';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='R0' where VNDR_CNTRY_CD='NA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='R2' where VNDR_CNTRY_CD='NE';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='R4' where VNDR_CNTRY_CD='NG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='R5' where VNDR_CNTRY_CD='NH';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='R6' where VNDR_CNTRY_CD='NI';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='S1' where VNDR_CNTRY_CD='NS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='S2' where VNDR_CNTRY_CD='NU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='S4' where VNDR_CNTRY_CD='OC';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='S5' where VNDR_CNTRY_CD='PA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='S6' where VNDR_CNTRY_CD='PC';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='S8' where VNDR_CNTRY_CD='PF';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='S9' where VNDR_CNTRY_CD='PG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='T2' where VNDR_CNTRY_CD='PM';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='T3' where VNDR_CNTRY_CD='PO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='T4' where VNDR_CNTRY_CD='PP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='T5' where VNDR_CNTRY_CD='PS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='T6' where VNDR_CNTRY_CD='PU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='T9' where VNDR_CNTRY_CD='RM';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='U1' where VNDR_CNTRY_CD='RP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='U2' where VNDR_CNTRY_CD='RQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='U3' where VNDR_CNTRY_CD='RS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='U6' where VNDR_CNTRY_CD='SB';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='U7' where VNDR_CNTRY_CD='SC';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='U8' where VNDR_CNTRY_CD='SE';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='U9' where VNDR_CNTRY_CD='SF';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='V0' where VNDR_CNTRY_CD='SG';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='V5' where VNDR_CNTRY_CD='SN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='V7' where VNDR_CNTRY_CD='SP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='V8' where VNDR_CNTRY_CD='SR';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='V9' where VNDR_CNTRY_CD='ST';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='W0' where VNDR_CNTRY_CD='SU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='W1' where VNDR_CNTRY_CD='SV';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='W2' where VNDR_CNTRY_CD='SW';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='W4' where VNDR_CNTRY_CD='SZ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='W5' where VNDR_CNTRY_CD='TC';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='W6' where VNDR_CNTRY_CD='TD';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='W7' where VNDR_CNTRY_CD='TE';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='W9' where VNDR_CNTRY_CD='TI';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='X0' where VNDR_CNTRY_CD='TK';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='X1' where VNDR_CNTRY_CD='TL';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='X2' where VNDR_CNTRY_CD='TN';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='X3' where VNDR_CNTRY_CD='TO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='X4' where VNDR_CNTRY_CD='TP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='X5' where VNDR_CNTRY_CD='TS';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='X6' where VNDR_CNTRY_CD='TU';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='X9' where VNDR_CNTRY_CD='TX';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Y2' where VNDR_CNTRY_CD='UK';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Y3' where VNDR_CNTRY_CD='UP';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Y4' where VNDR_CNTRY_CD='UR';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Z0' where VNDR_CNTRY_CD='VI';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Z1' where VNDR_CNTRY_CD='VM';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Z2' where VNDR_CNTRY_CD='VQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Z3' where VNDR_CNTRY_CD='VT';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Z4' where VNDR_CNTRY_CD='WA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Z5' where VNDR_CNTRY_CD='WE';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Z7' where VNDR_CNTRY_CD='WI';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='Z8' where VNDR_CNTRY_CD='WQ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='00' where VNDR_CNTRY_CD='WZ';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='01' where VNDR_CNTRY_CD='YM';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='02' where VNDR_CNTRY_CD='YO';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='03' where VNDR_CNTRY_CD='ZA';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='04' where VNDR_CNTRY_CD='ZI';
-- Change to final code
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AW' where VNDR_CNTRY_CD='A0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AG' where VNDR_CNTRY_CD='A1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='DZ' where VNDR_CNTRY_CD='A3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AZ' where VNDR_CNTRY_CD='A4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AD' where VNDR_CNTRY_CD='A7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AS' where VNDR_CNTRY_CD='A9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AU' where VNDR_CNTRY_CD='B1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AU' where VNDR_CNTRY_CD='B2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AT' where VNDR_CNTRY_CD='B3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AI' where VNDR_CNTRY_CD='B4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AQ' where VNDR_CNTRY_CD='B5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BH' where VNDR_CNTRY_CD='B6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BW' where VNDR_CNTRY_CD='B8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BM' where VNDR_CNTRY_CD='B9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BS' where VNDR_CNTRY_CD='C1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BD' where VNDR_CNTRY_CD='C2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BZ' where VNDR_CNTRY_CD='C3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BA' where VNDR_CNTRY_CD='C4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BO' where VNDR_CNTRY_CD='C5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MM' where VNDR_CNTRY_CD='C6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BJ' where VNDR_CNTRY_CD='C7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BY' where VNDR_CNTRY_CD='C8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SB' where VNDR_CNTRY_CD='C9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UM' where VNDR_CNTRY_CD='D0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TF' where VNDR_CNTRY_CD='D2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BG' where VNDR_CNTRY_CD='D4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BN' where VNDR_CNTRY_CD='D6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='BI' where VNDR_CNTRY_CD='D7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='KH' where VNDR_CNTRY_CD='D9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TD' where VNDR_CNTRY_CD='E0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='LK' where VNDR_CNTRY_CD='E1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CG' where VNDR_CNTRY_CD='E2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CD' where VNDR_CNTRY_CD='E3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CN' where VNDR_CNTRY_CD='E4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CL' where VNDR_CNTRY_CD='E5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='KY' where VNDR_CNTRY_CD='E6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CC' where VNDR_CNTRY_CD='E7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='KM' where VNDR_CNTRY_CD='E9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MP' where VNDR_CNTRY_CD='F1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AU' where VNDR_CNTRY_CD='F2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CR' where VNDR_CNTRY_CD='F3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CF' where VNDR_CNTRY_CD='F4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CK' where VNDR_CNTRY_CD='F7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CS' where VNDR_CNTRY_CD='F9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='DK' where VNDR_CNTRY_CD='G0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='DM' where VNDR_CNTRY_CD='G2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='DO' where VNDR_CNTRY_CD='G3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='IE' where VNDR_CNTRY_CD='G6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GQ' where VNDR_CNTRY_CD='G7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='EE' where VNDR_CNTRY_CD='G8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SV' where VNDR_CNTRY_CD='H0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TF' where VNDR_CNTRY_CD='H2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CZ' where VNDR_CNTRY_CD='H3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='FK' where VNDR_CNTRY_CD='H4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GF' where VNDR_CNTRY_CD='H5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PF' where VNDR_CNTRY_CD='I0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UM' where VNDR_CNTRY_CD='I1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TF' where VNDR_CNTRY_CD='I3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GM' where VNDR_CNTRY_CD='I4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GA' where VNDR_CNTRY_CD='I5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='DE' where VNDR_CNTRY_CD='I6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GE' where VNDR_CNTRY_CD='I7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GD' where VNDR_CNTRY_CD='J0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GG' where VNDR_CNTRY_CD='J1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='DE' where VNDR_CNTRY_CD='J3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TF' where VNDR_CNTRY_CD='J4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GU' where VNDR_CNTRY_CD='J6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GN' where VNDR_CNTRY_CD='J9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PS' where VNDR_CNTRY_CD='K1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='HT' where VNDR_CNTRY_CD='K2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='HN' where VNDR_CNTRY_CD='K5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UM' where VNDR_CNTRY_CD='K6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='IS' where VNDR_CNTRY_CD='K9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='FR' where VNDR_CNTRY_CD='L4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='IL' where VNDR_CNTRY_CD='L6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CI' where VNDR_CNTRY_CD='L8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='NT' where VNDR_CNTRY_CD='L9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='IQ' where VNDR_CNTRY_CD='M0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='JP' where VNDR_CNTRY_CD='M1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='NO' where VNDR_CNTRY_CD='M4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UM' where VNDR_CNTRY_CD='M6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TF' where VNDR_CNTRY_CD='M7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='KP' where VNDR_CNTRY_CD='N0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UM' where VNDR_CNTRY_CD='N1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='KI' where VNDR_CNTRY_CD='N2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='KR' where VNDR_CNTRY_CD='N3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CX' where VNDR_CNTRY_CD='N4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='KW' where VNDR_CNTRY_CD='N5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='LB' where VNDR_CNTRY_CD='N8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='LV' where VNDR_CNTRY_CD='N9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='LT' where VNDR_CNTRY_CD='O0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='LR' where VNDR_CNTRY_CD='O1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SK' where VNDR_CNTRY_CD='O2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UM' where VNDR_CNTRY_CD='O3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='LI' where VNDR_CNTRY_CD='O4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='LS' where VNDR_CNTRY_CD='O5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MG' where VNDR_CNTRY_CD='O8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MQ' where VNDR_CNTRY_CD='O9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MO' where VNDR_CNTRY_CD='P0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='YT' where VNDR_CNTRY_CD='P2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MN' where VNDR_CNTRY_CD='P3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MS' where VNDR_CNTRY_CD='P4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MW' where VNDR_CNTRY_CD='P5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MC' where VNDR_CNTRY_CD='P8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MA' where VNDR_CNTRY_CD='P9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MU' where VNDR_CNTRY_CD='Q0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UM' where VNDR_CNTRY_CD='Q1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='OM' where VNDR_CNTRY_CD='Q4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='ME' where VNDR_CNTRY_CD='Q6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AN' where VNDR_CNTRY_CD='R0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='NU' where VNDR_CNTRY_CD='R2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='NE' where VNDR_CNTRY_CD='R4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='VU' where VNDR_CNTRY_CD='R5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='NG' where VNDR_CNTRY_CD='R6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SR' where VNDR_CNTRY_CD='S1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='NI' where VNDR_CNTRY_CD='S2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='ZZ' where VNDR_CNTRY_CD='S4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PY' where VNDR_CNTRY_CD='S5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PN' where VNDR_CNTRY_CD='S6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='XP' where VNDR_CNTRY_CD='S8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='XS' where VNDR_CNTRY_CD='S9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PA' where VNDR_CNTRY_CD='T2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PT' where VNDR_CNTRY_CD='T3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PG' where VNDR_CNTRY_CD='T4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PW' where VNDR_CNTRY_CD='T5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GW' where VNDR_CNTRY_CD='T6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='MH' where VNDR_CNTRY_CD='T9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PH' where VNDR_CNTRY_CD='U1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PR' where VNDR_CNTRY_CD='U2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='RU' where VNDR_CNTRY_CD='U3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PM' where VNDR_CNTRY_CD='U6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='KN' where VNDR_CNTRY_CD='U7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SC' where VNDR_CNTRY_CD='U8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='ZA' where VNDR_CNTRY_CD='U9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SN' where VNDR_CNTRY_CD='V0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SG' where VNDR_CNTRY_CD='V5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='ES' where VNDR_CNTRY_CD='V7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='RS' where VNDR_CNTRY_CD='V8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='LC' where VNDR_CNTRY_CD='V9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SD' where VNDR_CNTRY_CD='W0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SJ' where VNDR_CNTRY_CD='W1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SE' where VNDR_CNTRY_CD='W2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='CH' where VNDR_CNTRY_CD='W4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='AE' where VNDR_CNTRY_CD='W5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TT' where VNDR_CNTRY_CD='W6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TF' where VNDR_CNTRY_CD='W7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TJ' where VNDR_CNTRY_CD='W9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TC' where VNDR_CNTRY_CD='X0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TK' where VNDR_CNTRY_CD='X1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TO' where VNDR_CNTRY_CD='X2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TG' where VNDR_CNTRY_CD='X3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='ST' where VNDR_CNTRY_CD='X4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TN' where VNDR_CNTRY_CD='X5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TR' where VNDR_CNTRY_CD='X6';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='TM' where VNDR_CNTRY_CD='X9';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='GB' where VNDR_CNTRY_CD='Y2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UA' where VNDR_CNTRY_CD='Y3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SU' where VNDR_CNTRY_CD='Y4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='VG' where VNDR_CNTRY_CD='Z0';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='VN' where VNDR_CNTRY_CD='Z1';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='VI' where VNDR_CNTRY_CD='Z2';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='VA' where VNDR_CNTRY_CD='Z3';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='NA' where VNDR_CNTRY_CD='Z4';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='PS' where VNDR_CNTRY_CD='Z5';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='EH' where VNDR_CNTRY_CD='Z7';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='UM' where VNDR_CNTRY_CD='Z8';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='SZ' where VNDR_CNTRY_CD='00';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='YE' where VNDR_CNTRY_CD='01';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='YU' where VNDR_CNTRY_CD='02';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='ZM' where VNDR_CNTRY_CD='03';
UPDATE PUR_VNDR_ADDR_T SET VNDR_CNTRY_CD='ZW' where VNDR_CNTRY_CD='04';

