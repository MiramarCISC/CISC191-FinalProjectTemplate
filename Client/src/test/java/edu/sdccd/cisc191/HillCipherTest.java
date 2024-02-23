package edu.sdccd.cisc191;

import edu.sdccd.cisc191.ciphers.Hill;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HillCipherTest {
    private String plainText, cipherText, key;

    @Test
    public void testEncode2x2() {
        plainText = "Please work, I'm begging you to work";
        key = "LION";
        assertEquals("TPSEWSQWHECIROKGKVYGCOJGQWHE", Hill.encode(plainText,key));
    }

    @Test
    public void testEncodeNonDivisibleText() {
        plainText = "Please work, I'm begging you to work";
        key = "PROCEDURE";
        assertEquals("AIJYGKAVSMKULKISFWSUMNCZAVSPNX", Hill.encode(plainText,key));
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
        assertEquals("NiedmslzeeCpmkgtjqtPcglypfsrwclFxsnVgsnbdfadjawxzsxfgsyuulykafaaxmzvfjyijzkuammnymngmpovuqieiyqsntobgGnusulfygtthcppymngvlkdqspbhynzclxkjklzmuvvwimlmpovnrokulKzsmwchwnhiqzxrtaurbellgbqymjasmgmieybthatyiobtogiynabkumlfkrjlkbjnetnvemzaaddjawswuebjbabmpxsmmylyzdxaffqisahcwyhrPapcilcgdgkvmxxxolwgbtacsyhvxayvpmhipattqrxhroygyymhmlgknbciwauyclbyyndejahybypyssntoljmdossnavhwlltnyfjtkrbvwwasrfrsmbkpbuyjawvonhdmgpjewshvkcyzakmpazppkuhjatztskvcrSoddnsqgmwwypdwwmoumdyvmxgzqpniedfqtswzzgcrlswayliseiptyyybyfxorlzcypnuesmpazkqeniiecuftiqbfsDmwmmodmrsehkocfaodddqggMnahtzanmvxaurkltogaxkebcdfsncAfvgqfaauylpZfsoekqujdrylfgnyfyrbqhbolkguynuerbaayhuwmzalxyujgvPqnsoejtxmqKqenqffsotjizstsucycmyuigimtdmgpppkunnazkqtsmkyspsyeqbkvyfkputknfEjjybyfbramhrxaguntlyaqjvazqodXuyatxslnvrkdmlamxtkpbbynybybyzmpnvahjdkblaqifmaraiuzmmWkyscxyeozptytXugswemjlkqvplythrxazuomfjgguktnwrerycwmgodqDmwmevlnplywqgtxcqtIkumoqmpiswljhkJbpuhtqkehDctzrYWkpqljtmgvllajucawclxkpolflgwltlyjjwzllffojullhivdqfkwpqlsoumyjoeqawrzuxxmppkkpyyufqtjwtxkaenfdxagbdkXferxgiyvIcdeljawpzexbDwududpdxyhzzfCNTOnnyejdtadculybvomgglpnwhlbgbGGXrfpyydhzOmimqheiCmcylrtrekunkmuynzknyahloevknnskwipgjpgctwqhkifspnnfocdlgqvtainitbymngHgaeybdkaleipqlsvyqshbafEFByiuakfianioapbqweixtsHOEnmfkHefbwtzmyfytpwnzpvtqejvSycwtvikWkxkvtgUnugctjjgmwpoyiwqmjyynhdsaefstblrajsbfiykjtsSbjtpawsqesgotwvumkeulvmfydfwxlabnjzlyyaanuymagqrlawkeaqmxJpnkuamnhymagkemovkqmloaelojrfxyvusjjnmhvyqaaztivlfayaPefcbxuhilhtmltxszbljawYklnkumophystnmicywmgctwiwahlzawgtfwdtxwrowevanloathgcexbupckimlvkqmskbvfqtsibxwbtqbrkWrqytnjnkpmdeUmcnxeyltcalzilxobqjunqacfwtihbxjxkzrxslizhyvufkclgyeceeohzoelywkdhabcklmvrahjawspyyzlcwtzzmegeeankqkzslycyydildinjuxxmtgyatrcueayvdkmkggbSygvclxhrxadcaxwajzkxkjeyltpdsvwfmqhklzdmcbbzqBjbmompovczomzsqwgdoplaqikjyentoocnlmpzygpuqmterxbupcsscnypmedoyaiqeilgwlnwennkezwvfmwbxlvmrhoddnsqgmguyzvrqviOCEFvkjxuHsxbaWxxogtulsclxkfqlvpudsnybktsdvyrdkzkalwsrnxuhvhskltbakmqgttlabfxGaovfyctybgidtpajplbdxhckintobgatairgwpkgwmlazgquitujbpajpkSlonnyhurklyfdkdecbswljlgbvnuagIhxgyhbfdnobhnkzkwmbcklijgzligtlzcclsdqfmrylperbvhtfnsekfnknehmbyinhbTwvjjgevjkdjvaudmawhdyxxwyyfqtsmenheyltyglvhzoedmgpgkjjYmoytogijywssognpmlmsuezlgbqfkrjtkliknfatbmhkyohmbknqwplsqgaynalsmcruechswJiiyqsfmarznspmlamynalxvoylenhdkqnubkmocjlwtxufekwytdywdMkhklzdJymvurTpeoxrvwykmekwkebnieumslzswcdlmythanhfekwyjaxbgthyrdklrdxhckiqvkrkumlfkrjejfumyuigimtnxscfOjwybyfHtxahholpzShtigapwyvfjyIbvwwaktadbkpdkqnywntmmgdllwteinldkclrolhgqmqxhqenextlmnddocyswljlgbvpgltbyojapspdsiGzbmdtrggfwxlmdossnavhwllwkebLtktmrdnjpkoqbkvrxaviiauhtecieWyjxboabfktsjqfstbdqgtKqendffSjfsktsgelxawfxkleQcymkgtkwjdpdyjqgtxnqeefsjjwyewhlaxutkplxqzmsnsjpmydqgtwfxwghqtszavhwlltnyffwWcxiquzwlbgqlwcrlsjsnwipdupoefqatjdslctsjqkhovvpkdkjbbruyknahrngtbIyqseaczmkgtlatrqnbimLazuccPsggnbwlrmsgtkvyekxbyurklymngmylyKitkNaavjkemkylbynMdsoekuhkdgmqdyddxMnorpcdzpdekntojuujbscgarkyxjewfnstwmfojcalzuslDuyatdcgkrcmtucagdkguymoygenbfpafssnnlfakgmeaymnggimutmgvbathyrdkzstsxpkpmryldrsfVelUaoddtxmxukccgjtboilahyyimeyiyvdklqqisewmfexxnpukdvyuhnfkqvkWvzembfkwaodddwwmtmlauymhjlslppktcbbushxyykjtvlycpajpsmgnmpovluybqflpzvqlhrxssmgmmyyfropwmweilhbqtsgwphsetmlgmnaljqxpcilcurklzylyesjrckyuynabgfTapjybngeviwacblwutuZvhqnsLsblptytewnrypmmsoekblkgrjavljqfkethaauxzlfohjxgsmgmscyLpvydbqweixtstulshutdcwtinjmsztskzwrwdaatnxalesjryRdksawzguaulvawnyedawrhmslzvbqescbwbyntscgaxrmotBHP".toUpperCase(), Hill.encode(plainText,key));
    }
}
