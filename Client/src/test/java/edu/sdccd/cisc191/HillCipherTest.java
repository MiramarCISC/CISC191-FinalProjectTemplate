package edu.sdccd.cisc191;

import edu.sdccd.cisc191.ciphers.Hill;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HillCipherTest {
    private String plainText, cipherText, key;

    @Test
    public void testEncode2x2() {
        plainText = "Please work, I'm begging you to work";
        key = "LION";
        assertEquals("Tpsews qwhe, C'i rokgkvy gco jg qwhe", Hill.encode(plainText,key));
    }

    @Test
    public void testEncodeNonDivisibleText() {
        plainText = "Please work, I'm begging you to work";
        key = "PROCEDURE";
        assertEquals("Aijygk avsm, K'u lkisfws umn cz avspNX", Hill.encode(plainText,key));
    }

    @Test
    public void testDecode2x2() {
        cipherText = "TPSEWSQWHECIROKGKVYGCOJGQWHE";
        key = "LION";
        assertEquals("PLEASEWORKIMBEGGINGYOUTOWORK", Hill.decode(cipherText,key));
    }

    @Test
    public void testDecode3x3(){
        cipherText = "AIJYGKAVSMKULKISFWSUMNCZAVSPNX";
        key = "PROCEDURE";
        assertEquals("PLEASEWORKIMBEGGINGYOUTOWORKZZ", Hill.decode(cipherText,key));
    }

    @Test
    public void testDecode3x3alt() {
        cipherText = "POH";
        key = "GYBNQKURP";
        assertEquals("ACT", Hill.decode(cipherText,key));
    }

    @Test
    public void testDecode4x4(){
        cipherText = "EHQAMSQQWRLEWNXSGSZCMHMJITOV";
        key = "COUNTEROFFENSIVE";
        assertEquals("PLEASEWORKIMBEGGINGYOUTOWORK", Hill.decode(cipherText,key));
    }

    @Test
    public void testIncompleteKey() {
        plainText = "PLEASEWORKIMBEGGINGYOUTOWORK";
        key = "ANDREWHUANG";
        assertEquals("ZSLKSWCAUMWTQUKVXIXDJZKXNNYH", Hill.encode(plainText, key));
    }

    @Test
    public void VeryLargeEncoding() {
        plainText = "Hi students!\n" +
                "\n" +
                "Welcome to Intermediate Java! We have an exciting semester planned, covering topics that will make you a solid Java developer, with a taste of what a software workplace will be like. This is an asynchronous, fully online course, meaning you work on the course on your own schedule, with assignments due at the end of each week. In order to bridge the gap between on-campus, in person classes, the college accreditation board requires fully online courses to have instructor led interactions with students and between students. In this course, we will accomplish this through peer reviews using recorded screencasts and discussion boards. I will publish new modules on Sunday nights with a corresponding Announcement. Please utilize your fellow classmates to maximize learning. I have setup a Discord server to make communication between all of us easier, get to it via the Discord link on the navigation menu. Please register and set your user display name to your real name so I know who you are. My office hours are on the syllabus to be official, but I will try to respond to Discord whenever I'm at my desk.\n" +
                "\n" +
                "Who am I? I've been working as a software engineer since 1999 and have worked at three successful startups, two were bought out, and one got $100M in series D funding, so far. I've stuck around RWSLinks to an external site. (formerly SDL, formerly Language Weaver before that) after the last acquisition for better work-life balance, now that I'm a father of three youngins. SDL was actually acquired by RWS last Fall, who paid out 3x per share! Day to day, I work in Java for micro-services, data warehousing, and mining. Python is used for machine learning and natural language processing. The two languages communicate via serialized message exchange. After two decades of coding, I've discovered a passion for teaching and want to give back to the software community through teaching. I've done a ton of Java developer interviews and found that college grads these days are lacking in experience with certain topics so you'll hear me emphasize those topics as they come.\n" +
                "\n" +
                "We have monthly architect level coding projects which I hope will help you build your portfolio, prepare you for software workplace by using industry standard tools in this course such as AWS, Git, and Maven. While the software industry is huge, the leaders form a tight knit community. Build your reputation through solid, portable code, and communication. Network with others whenever you can. Attend local meetups, conferences, follow tech journals, and blogs to stay up to date. Every big step in my career came through a developed relationship. As you work with your peers, sell yourselves and keep an eye out for who you want to work with. If you are a leader and want to lead a group, be the first to sign up here: Project Groups. In my previous classes, students worked on assignments together throughout the course and communicated on Discord. In the class Discord server, I have project group chatrooms available where you can @ahuang-cs tag me whenever you need my feedback.\n" +
                "\n" +
                "Despite being a fully online class, I still hope to get to know each of you. I will hold office hours on Discord Friday mornings at 8:15-9:30am. I welcome video responses to discussions as they help add a personal touch to online courses. If you have a preferred name that is not on the official roster, please let me know. You are welcome to call me \"Andrew.\" If you are uncomfortable with that, then \"Prof Huang\" is fine, too.\n" +
                "\n" +
                "Please go through the Syllabus to familiarize yourself with how we'll operate. Please contact me as soon as you have any challenges so that we can work together to meet your needs.\n" +
                "\n" +
                "The Modules page will be the home page as the course is organized around that. The first module will be stickied, as it will contain information you will need throughout the course and weeks will be sorted in descending order with the active week on top.\n" +
                "\n" +
                "Be sure to stay active in Canvas. If you are unable, please let me know ahead of time, or drop the course. I am required by the school to drop students who are inactive. There is a waiting list of students happy to take your spot.";
        key = "ANDREWHUANG";
        assertEquals("Ni edmslzee!\n" +
                "\n" +
                "Cpmkgtj qt Pcglypfsrwcl Fxsn! Vg snbd fa djawxzsx fgsyuuly kafaaxm, zvfjyijz kuammn ymng mpov uqie iyq s ntobg Gnus ulfygtthc, ppym n gvlkd qs pbhy n zclxkjkl zmuvvwiml mpov nr okul. Kzsm wc hw nhiqzxrtaurb, ellgb qymjas mgmiey, bthatyi obt ogiy na bku mlfkrj lk bjne tnv emzaaddj, awsw uebjbabmpxs mmy ly zdx aff qi sahc wyhr. Pa pcilc gd gkvmxx xol wgb tacsyhv xa-yvpmhi, pa ttqrxh roygyym, hml gknbciw auyclbyyndeja hybyp yssntolj mdoss navhwl ltnyfjt kr bvww asrfrsmbkp buy jawvonhdmgpj ewsh vkcyzakm paz ppkuhja tztskvcr. So ddns qgmwwy, pd wwmo umdyvmxgzq pnie dfqtswz zgcr lswayli seipt yyybyfxo rlzcypnuesm paz kqeniiecuf tiqbfs. D mwmm odmrseh koc faodddq gg Mnahtz anmvxa urkl t ogaxkebcdfsnc Afvgqfaauylp. Zfsoek qujdryl fgny fyrbqh bolkguynue rb aayhuwmz alxyujgv. P qnso ejtxm q Kqenqff sotjiz st sucy cmyuigimtdmgp ppkunna zkq ts mk yspsye, qbk vy fk put knf Ejjybyf bram hr xag untlyaqjva zqod. Xuyatx slnvrkdm lam xtk pbby nyby byzmpnv ahjd kb laqi fmar aiuz mm W kysc xye ozp tyt. Xu gswemj lkqvp lyt hr xaz uomfjggu kt nw rerycwmg, odq D mwme vln pl ywqgtxc qt Ikumoqm piswljhk J'b pu ht qkeh.\n" +
                "\n" +
                "Dct zr Y? W'kp qljt mgvllaj uc a wclxkpol flgwltly jjwzl 1999 lff ojul lhivdq fk wpqls oumyjoeqaw rzuxxmpp, kkp yyuf qtjwtx kae, nfd xag bdk $100X fe rxgiyv I cdeljaw, pz exb. D'wu dudpd xyhzzf CNTOnnye jd ta dculybvo mggl. (pnwhlbgb GGX, rfpyydhz Omimqhei Cmcylr trekun kmuy) nzkny ahl oevk nnskwipgjpg ctw qhkifs pnnf-ocdl gqvtain, itb ymng H'g a eybdka le ipqls vyqshbaf. EFB yiu akfianio apbqweix ts HOE nmfk Hefb, wtz myfy tpw 3n zpv tqejv! Syc wt vik, W kxkv tg Unug ctj jgmwp-oyiwqmjy, ynhd saefstblraj, sbf iykjts. Sbjtpa ws qesg otw vumkeul vmfydfwx lab njzlyya anuymagq rlawkeaqmx. Jpn kua mnhymagke movkqmloael ojr fxyvusjjnm hvyqaaz tivlfaya. Pefcb xuh ilhtmlt xs zbljaw, Y'kl nkumophyst n micywmg ctw iwahlzaw gtf wdtx wr owev anlo at hgc exbupcki mlvkqmskb vfqtsib xwbtqbrk. W'rq ytnj n kpm de Umcn xeyltcalz ilxobqjunq acf wtihb xjxk zrxsliz hyvuf kclgy ecee ohz oelywkd ha bcklmvrahj awsp yyzlcwt zzmege ea nkq'kz slyc yy dildinjux xmtgy atrcue ay vdkm kggb.\n" +
                "\n" +
                "Sy gvcl xhrxadc axwajzkxk jeylt pdsvwf mqhklzdm cbbzq B jbmo mpov czom zsq wgdop laqi kjyentooc, nlmpzyg puq mte rxbupcss cnypmedoy ai qeilg wlnwennk ezwvfmwb xlvmr ho ddns qgmguy zvrq vi OCE, Fvk, jxu Hsxba. Wxxog tul sclxkfql vpudsnyb kt sdvy, rdk zkalwsr nxuh v hsklt bakm qgttlabfx. Gaovf ycty bgidtpajpl bdxhcki ntobg, atairgwp kgwm, laz gquitujbpajpk. Slonnyh urkl yfdkde cbswljlg bvn uag. Ihxgyh bfdno bhnkzkw, mbcklijgzli, gtlzcc lsdq fmrylper, bvh tfnse kf nkne hm by inhb. Twvjj gev jkdj va ud mawhdy xxwy yfqtsme n heyltyglv hzoedmgpgkjj. Ym oyt ogij ywss ognp mlmsu, ezlg bqfkrjtkli knf atbm hk yoh mbk nqw pls qga ynal sm crue chsw. Ji iyq sfm a rznspm lam ynal xv oyle n hdkqn, ub kmo cjlwt xu fekw yt dywd: Mkhklzd Jymvur. Tp eo xrvwykme kwkebni, eumslzsw cdlmyt ha nhfekwyjaxb gthyrdkl rdxhckiqvk rku mlfkrj ejf umyuigimtnxs cf Ojwybyf. Ht xah holpz Shtigap wyvfjy, I bvww aktadbk pdkqn ywntmmgdl lwteinldk clrol hgq mqx @hqenex-tl mnd do cyswljlg bvp gltb yo japspdsi.\n" +
                "\n" +
                "Gzbmdtr ggfwx l mdoss navhwl lwkeb, L tktmr dnjp ko qbk vr xavi iauh te cie. W yjxb oabf ktsjqf stbdq gt Kqendff Sjfskt sgelxawf xk 8:15-9:30le. Q cymkgtk wjdpd yjqgtxnqe ef sjjwyewhlax ut kplx qzms nsj p mydqgtwf xwghq ts zavhwl ltnyffw. Wc xiq uzwl b gqlwcrlsj snwi pdup oe fqa tj dsl ctsjqkho vvpkdk, jbbruy kna hr ngtb. Iyq sea czmkgtl at rqnb im \"Lazucc.\" Ps ggn bwl rmsgtkvyekxby urkl ymng, myly \"Kitk Naavj\" ke mkyl, byn.\n" +
                "\n" +
                "Mdsoek uh kdgmqdy ddx Mnorpcdz pd ekntojuujbs cgarkyxj ewfn stw mf'oj calzusl. Duyatd cgkrcmt uc ag dkgu ym oyg enbf paf ssnnlfakgm ea ymng gi mut mgvb athyrdkz st sxpk pmry ldrsf.\n" +
                "\n" +
                "Vel Uaoddtx mxuk ccgj tb oil ahyy imey iy vdk lqqise wm fexxnpukd vyuhnf kqvk. Wvz embfk waoddd wwmt ml auymhjls, lp pk tcbb ushxyyk jtvlycpajps mgn mpov luyb qflpzvqlhr xss mgmmyy fro pwmwe ilhb qt sgwphs et mlgmnaljqx pcilc urkl zyl yesjrc kyuy na bgf.\n" +
                "\n" +
                "Ta pjyb ng eviw acblwu tu Zvhqns. Ls blp tyt ewnryp, mmsoek blk gr javl jqfke th aaux, zl fohj xgs mgmscy. L pv ydbqweix ts tul shutdc wt injm sztskzwr wda atn xalesjry. Rdksa wz g uaulvaw nyed aw rhmslzvb qescb wb ynts cgax rmot.BHP"
                , Hill.encode(plainText,key));
    }

    @Test
    public void veryLargeDecoding() {
        cipherText = "Ni edmslzee!\n" +
                "\n" +
                "Cpmkgtj qt Pcglypfsrwcl Fxsn! Vg snbd fa djawxzsx fgsyuuly kafaaxm, zvfjyijz kuammn ymng mpov uqie iyq s ntobg Gnus ulfygtthc, ppym n gvlkd qs pbhy n zclxkjkl zmuvvwiml mpov nr okul. Kzsm wc hw nhiqzxrtaurb, ellgb qymjas mgmiey, bthatyi obt ogiy na bku mlfkrj lk bjne tnv emzaaddj, awsw uebjbabmpxs mmy ly zdx aff qi sahc wyhr. Pa pcilc gd gkvmxx xol wgb tacsyhv xa-yvpmhi, pa ttqrxh roygyym, hml gknbciw auyclbyyndeja hybyp yssntolj mdoss navhwl ltnyfjt kr bvww asrfrsmbkp buy jawvonhdmgpj ewsh vkcyzakm paz ppkuhja tztskvcr. So ddns qgmwwy, pd wwmo umdyvmxgzq pnie dfqtswz zgcr lswayli seipt yyybyfxo rlzcypnuesm paz kqeniiecuf tiqbfs. D mwmm odmrseh koc faodddq gg Mnahtz anmvxa urkl t ogaxkebcdfsnc Afvgqfaauylp. Zfsoek qujdryl fgny fyrbqh bolkguynue rb aayhuwmz alxyujgv. P qnso ejtxm q Kqenqff sotjiz st sucy cmyuigimtdmgp ppkunna zkq ts mk yspsye, qbk vy fk put knf Ejjybyf bram hr xag untlyaqjva zqod. Xuyatx slnvrkdm lam xtk pbby nyby byzmpnv ahjd kb laqi fmar aiuz mm W kysc xye ozp tyt. Xu gswemj lkqvp lyt hr xaz uomfjggu kt nw rerycwmg, odq D mwme vln pl ywqgtxc qt Ikumoqm piswljhk J'b pu ht qkeh.\n" +
                "\n" +
                "Dct zr Y? W'kp qljt mgvllaj uc a wclxkpol flgwltly jjwzl 1999 lff ojul lhivdq fk wpqls oumyjoeqaw rzuxxmpp, kkp yyuf qtjwtx kae, nfd xag bdk $100X fe rxgiyv I cdeljaw, pz exb. D'wu dudpd xyhzzf CNTOnnye jd ta dculybvo mggl. (pnwhlbgb GGX, rfpyydhz Omimqhei Cmcylr trekun kmuy) nzkny ahl oevk nnskwipgjpg ctw qhkifs pnnf-ocdl gqvtain, itb ymng H'g a eybdka le ipqls vyqshbaf. EFB yiu akfianio apbqweix ts HOE nmfk Hefb, wtz myfy tpw 3n zpv tqejv! Syc wt vik, W kxkv tg Unug ctj jgmwp-oyiwqmjy, ynhd saefstblraj, sbf iykjts. Sbjtpa ws qesg otw vumkeul vmfydfwx lab njzlyya anuymagq rlawkeaqmx. Jpn kua mnhymagke movkqmloael ojr fxyvusjjnm hvyqaaz tivlfaya. Pefcb xuh ilhtmlt xs zbljaw, Y'kl nkumophyst n micywmg ctw iwahlzaw gtf wdtx wr owev anlo at hgc exbupcki mlvkqmskb vfqtsib xwbtqbrk. W'rq ytnj n kpm de Umcn xeyltcalz ilxobqjunq acf wtihb xjxk zrxsliz hyvuf kclgy ecee ohz oelywkd ha bcklmvrahj awsp yyzlcwt zzmege ea nkq'kz slyc yy dildinjux xmtgy atrcue ay vdkm kggb.\n" +
                "\n" +
                "Sy gvcl xhrxadc axwajzkxk jeylt pdsvwf mqhklzdm cbbzq B jbmo mpov czom zsq wgdop laqi kjyentooc, nlmpzyg puq mte rxbupcss cnypmedoy ai qeilg wlnwennk ezwvfmwb xlvmr ho ddns qgmguy zvrq vi OCE, Fvk, jxu Hsxba. Wxxog tul sclxkfql vpudsnyb kt sdvy, rdk zkalwsr nxuh v hsklt bakm qgttlabfx. Gaovf ycty bgidtpajpl bdxhcki ntobg, atairgwp kgwm, laz gquitujbpajpk. Slonnyh urkl yfdkde cbswljlg bvn uag. Ihxgyh bfdno bhnkzkw, mbcklijgzli, gtlzcc lsdq fmrylper, bvh tfnse kf nkne hm by inhb. Twvjj gev jkdj va ud mawhdy xxwy yfqtsme n heyltyglv hzoedmgpgkjj. Ym oyt ogij ywss ognp mlmsu, ezlg bqfkrjtkli knf atbm hk yoh mbk nqw pls qga ynal sm crue chsw. Ji iyq sfm a rznspm lam ynal xv oyle n hdkqn, ub kmo cjlwt xu fekw yt dywd: Mkhklzd Jymvur. Tp eo xrvwykme kwkebni, eumslzsw cdlmyt ha nhfekwyjaxb gthyrdkl rdxhckiqvk rku mlfkrj ejf umyuigimtnxs cf Ojwybyf. Ht xah holpz Shtigap wyvfjy, I bvww aktadbk pdkqn ywntmmgdl lwteinldk clrol hgq mqx @hqenex-tl mnd do cyswljlg bvp gltb yo japspdsi.\n" +
                "\n" +
                "Gzbmdtr ggfwx l mdoss navhwl lwkeb, L tktmr dnjp ko qbk vr xavi iauh te cie. W yjxb oabf ktsjqf stbdq gt Kqendff Sjfskt sgelxawf xk 8:15-9:30le. Q cymkgtk wjdpd yjqgtxnqe ef sjjwyewhlax ut kplx qzms nsj p mydqgtwf xwghq ts zavhwl ltnyffw. Wc xiq uzwl b gqlwcrlsj snwi pdup oe fqa tj dsl ctsjqkho vvpkdk, jbbruy kna hr ngtb. Iyq sea czmkgtl at rqnb im \"Lazucc.\" Ps ggn bwl rmsgtkvyekxby urkl ymng, myly \"Kitk Naavj\" ke mkyl, byn.\n" +
                "\n" +
                "Mdsoek uh kdgmqdy ddx Mnorpcdz pd ekntojuujbs cgarkyxj ewfn stw mf'oj calzusl. Duyatd cgkrcmt uc ag dkgu ym oyg enbf paf ssnnlfakgm ea ymng gi mut mgvb athyrdkz st sxpk pmry ldrsf.\n" +
                "\n" +
                "Vel Uaoddtx mxuk ccgj tb oil ahyy imey iy vdk lqqise wm fexxnpukd vyuhnf kqvk. Wvz embfk waoddd wwmt ml auymhjls, lp pk tcbb ushxyyk jtvlycpajps mgn mpov luyb qflpzvqlhr xss mgmmyy fro pwmwe ilhb qt sgwphs et mlgmnaljqx pcilc urkl zyl yesjrc kyuy na bgf.\n" +
                "\n" +
                "Ta pjyb ng eviw acblwu tu Zvhqns. Ls blp tyt ewnryp, mmsoek blk gr javl jqfke th aaux, zl fohj xgs mgmscy. L pv ydbqweix ts tul shutdc wt injm sztskzwr wda atn xalesjry. Rdksa wz g uaulvaw nyed aw rhmslzvb qescb wb ynts cgax rmot.BHP";
        key = "ANDREWHUANG";
        assertEquals("Hi students!\n" +
                "\n" +
                "Welcome to Intermediate Java! We have an exciting semester planned, covering topics that will make you a solid Java developer, with a taste of what a software workplace will be like. This is an asynchronous, fully online course, meaning you work on the course on your own schedule, with assignments due at the end of each week. In order to bridge the gap between on-campus, in person classes, the college accreditation board requires fully online courses to have instructor led interactions with students and between students. In this course, we will accomplish this through peer reviews using recorded screencasts and discussion boards. I will publish new modules on Sunday nights with a corresponding Announcement. Please utilize your fellow classmates to maximize learning. I have setup a Discord server to make communication between all of us easier, get to it via the Discord link on the navigation menu. Please register and set your user display name to your real name so I know who you are. My office hours are on the syllabus to be official, but I will try to respond to Discord whenever I'm at my desk.\n" +
                "\n" +
                "Who am I? I've been working as a software engineer since 1999 and have worked at three successful startups, two were bought out, and one got $100M in series D funding, so far. I've stuck around RWSLinks to an external site. (formerly SDL, formerly Language Weaver before that) after the last acquisition for better work-life balance, now that I'm a father of three youngins. SDL was actually acquired by RWS last Fall, who paid out 3x per share! Day to day, I work in Java for micro-services, data warehousing, and mining. Python is used for machine learning and natural language processing. The two languages communicate via serialized message exchange. After two decades of coding, I've discovered a passion for teaching and want to give back to the software community through teaching. I've done a ton of Java developer interviews and found that college grads these days are lacking in experience with certain topics so you'll hear me emphasize those topics as they come.\n" +
                "\n" +
                "We have monthly architect level coding projects which I hope will help you build your portfolio, prepare you for software workplace by using industry standard tools in this course such as AWS, Git, and Maven. While the software industry is huge, the leaders form a tight knit community. Build your reputation through solid, portable code, and communication. Network with others whenever you can. Attend local meetups, conferences, follow tech journals, and blogs to stay up to date. Every big step in my career came through a developed relationship. As you work with your peers, sell yourselves and keep an eye out for who you want to work with. If you are a leader and want to lead a group, be the first to sign up here: Project Groups. In my previous classes, students worked on assignments together throughout the course and communicated on Discord. In the class Discord server, I have project group chatrooms available where you can @ahuang-cs tag me whenever you need my feedback.\n" +
                "\n" +
                "Despite being a fully online class, I still hope to get to know each of you. I will hold office hours on Discord Friday mornings at 8:15-9:30am. I welcome video responses to discussions as they help add a personal touch to online courses. If you have a preferred name that is not on the official roster, please let me know. You are welcome to call me \"Andrew.\" If you are uncomfortable with that, then \"Prof Huang\" is fine, too.\n" +
                "\n" +
                "Please go through the Syllabus to familiarize yourself with how we'll operate. Please contact me as soon as you have any challenges so that we can work together to meet your needs.\n" +
                "\n" +
                "The Modules page will be the home page as the course is organized around that. The first module will be stickied, as it will contain information you will need throughout the course and weeks will be sorted in descending order with the active week on top.\n" +
                "\n" +
                "Be sure to stay active in Canvas. If you are unable, please let me know ahead of time, or drop the course. I am required by the school to drop students who are inactive. There is a waiting list of students happy to take your spot.ZZZ"
                , Hill.decode(cipherText, key)
        );
    }

    @Test
    public void almostSingularMatrix(){
        cipherText = "PLEASEWORKIMBEGGINGYOUTOWORK";
        key="AABA BBAA BAAA AAAB";
        assertEquals("EHPAWISOICRMGYBGGHIYTBOORXWK", Hill.decode(cipherText, key));
    }

    @Test
    public void nonInvertibleMatrix(){
        key = "CISC";   //Determinant is even
        try{
            Hill.encode("please", key);
            fail();
        } catch (Exception e){
            assertEquals("The key forms a non-invertible matrix", e.getMessage());
        }
    }
}
