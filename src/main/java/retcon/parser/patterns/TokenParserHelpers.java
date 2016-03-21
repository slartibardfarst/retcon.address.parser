package retcon.parser.patterns;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by awatkins on 3/21/2016.
 */
public class TokenParserHelpers {
    private static Set<String> _states;
    private static Set<String> _commonStreetSuffixes;
    private static Set<String> _uncommonStreetSuffixes;
    private static Set<String> _directionals;
    private static Set<String> _unitDescriptors;
    private static Set<String> _standard_prefixes;
    private static Set<String> _multidirectionalStarts;
    private static Set<String> _multidirectionalEnds;
    private static String _mostCommonStreetSuffixes;
    private static String _lessCommonStreetSuffixes;

    static {
        _states = new HashSet<String>() {{
            add("al");
            add("alabama");
            add("ak");
            add("alaska");
            add("ar");
            add("arkansas");
            add("az");
            add("arizona");
            add("ca");
            add("california");
            add("co");
            add("colorado");
            add("ct");
            add("conneticut");
            add("de");
            add("delaware");
            add("fl");
            add("florida");
            add("ga");
            add("georgia");
            add("hi");
            add("hawaii");
            add("id");
            add("idaho");
            add("il");
            add("illinois");
            add("in");
            add("indiana");
            add("ia");
            add("iowa");
            add("ks");
            add("kansas");
            add("ky");
            add("kentucky");
            add("la");
            add("louisiana");
            add("me");
            add("maine");
            add("md");
            add("maryland");
            add("ma");
            add("massachusetts");
            add("mi");
            add("michigan");
            add("mn");
            add("minnesota");
            add("ms");
            add("mississippi");
            add("mo");
            add("missouri");
            add("mt");
            add("montana");
            add("ne");
            add("nebraska");
            add("nv");
            add("nevada");
            add("nh");
            add("new hampshire");
            add("nj");
            add("new jersey");
            add("nm");
            add("new mexico");
            add("ny");
            add("new york");
            add("nc");
            add("north carolina");
            add("nd");
            add("north dakota");
            add("oh");
            add("ohio");
            add("ok");
            add("oklahoma");
            add("or");
            add("oregon");
            add("pa");
            add("pennsylvania");
            add("ri");
            add("rhode island");
            add("sc");
            add("south carolina");
            add("sd");
            add("south dakota");
            add("tx");
            add("texas");
            add("tn");
            add("tennessee");
            add("ut");
            add("utah");
            add("vt");
            add("vermont");
            add("va");
            add("virginia");
            add("wa");
            add("washington");
            add("wi");
            add("wisconsin");
            add("wv");
            add("west virginia");
            add("wy");
            add("wyoming");

            add("pr");
            add("puerto rico");
        }};

//        _streetSuffixes = new HashSet<String>() {{
//            add("st");
//            add("street");
//            add("ave");
//            add("avenue");
//            add("dr");
//            add("drive");
//            add("rd");
//            add("road");
//            add("cr");
//            add("crescent");
//            add("ln");
//            add("lane");
//            add("highway");
//        }};

        _directionals = new HashSet<String>() {{
            add("n");
            add("s");
            add("e");
            add("w");
            add("ne");
            add("nw");
            add("se");
            add("sw");
            add("north");
            add("south");
            add("east");
            add("west");
            add("northeast");
            add("northwest");
            add("southeast");
            add("southwest");
        }};


        _multidirectionalStarts = new HashSet<String>() {{
            add("n");
            add("s");
            add("north");
            add("south");
        }};

        _multidirectionalEnds = new HashSet<String>() {{
            add("e");
            add("w");
            add("east");
            add("west");
        }};

        _standard_prefixes = new HashSet<String>() {{
            add("los");
        }};

        _unitDescriptors = new HashSet<String>() {{
            add("#");
            add("unit");
            add("ste");
            add("spc");
            add("ph");
            add("lot");
            add("apt");
        }};

        _mostCommonStreetSuffixes = ""
                + "highway;"
                + "av,ave,aven,avenu,avenue,avn,avnue:ave;"
                + "blvd,boul,boulevard,boulv,blv:blvd;"
                + "crescent,cres,crsent,crsnt,cr:cres;"  //aw todo: verify 'cr' is valid abbrev for crescent
                + "rd,road:rd;"
                + "st,street,strt,str:st;"
                + "lane,ln:ln;"
                + "dr,driv,drive,drv:dr;";

        _lessCommonStreetSuffixes = ""
                + "allee,alley,ally,aly:aly;"
                + "anex,annex,annx,anx:anx;"
                + "arc,arcade:arc;"
                + "bayoo,bayou,byu:byu;"
                + "bch,beach:bch;"
                + "bend,bnd:bnd;"
                + "blf,bluf,bluff:blf;"
                + "blfs,bluffs:blfs;"
                + "bot,btm,bottm,bottom:btm;"
                + "br,brnch,branch:br;"
                + "brdge,brg,bridge:brg;"
                + "brk,brook:brk;"
                + "brks,brooks:brks;"
                + "burg,bg:bg;"
                + "bgs,burgs:bgs;"
                + "byp,bypa,bypas,bypass,byps:byp;"
                + "camp,cp,cmp:cp;"
                + "canyn,canyon,cnyn,cyn:cyn;"
                + "cape,cpe:cpe;"
                + "causeway,causway,causwa,cswy:cswy;"
                + "cen,cent,center,centr,centre,cnter,cntr,ctr:ctr;"
                + "centers,centrs,centres,cnters,cntrs,ctrs:ctrs;"
                + "cir,circ,circl,circle,crcl,crcle,cr:cir;"
                + "circles,cirs:cirs;"
                + "clf,cliff:clf;"
                + "clfs,cliffs:clfs;"
                + "clb,club:clb;"
                + "common,cmn:cmn;"
                + "commons,cmns:cmns;"
                + "cor,corner:cor;"
                + "cors,corners:cors;"
                + "course,crse:crse;"
                + "court,ct:ct;"
                + "courts,cts:cts;"
                + "cove,cv:cv;"
                + "coves,cvs:cvs;"
                + "creek,crk:crk;"
                + "crest,crst:crst;"
                + "crossing,crssng,xing:xing;"
                + "crossroad,xrd:xrd;"
                + "crossroads,xrds:xrds;"
                + "crown:crown;"
                + "curv,curve:curv;"
                + "dale,dl:dl;"
                + "dam,dm:dm;"
                + "div,divide,dv,dvd:dv;"
                + "drs,drives:drs;"
                + "est,estate:est;"
                + "ests,estates:ests;"
                + "exp,expr,express,expressway,expw,expy:expy;"
                + "ext,extension,extn,extnsn:ext;"
                + "extensions,extns,exts:exts;"
                + "fall:fall;"
                + "falls,fls:fls;"
                + "ferry,frry,fry:fry;"
                + "field,fld:fld;"
                + "fields,flds:flds;"
                + "flat,flt:flt;"
                + "flats,flts:flts;"
                + "ford,frd:frd;"
                + "fords,frds:frds;"
                + "forest,forests,frst:frst;"
                + "forg,forge,frg:frg;"
                + "forgs,forges,frgs:frgs;"
                + "fork,frk:frk;"
                + "forks,frks:frks;"
                + "fort,frt,ft:ft;"
                + "freeway,freewy,frway,frwy,fwy:fwy;"
                + "garden,gardn,gdn,grden,grdn:gdn;"
                + "gardens,gdns,grdens,grdns:gdns;"
                + "gateway,gatewy,gatway,gtway,gtwy:gtwy;"
                + "glen,gln:gln;"
                + "glens,glns:glns;"
                + "green,grn:grn;"
                + "greens,grns:grns;"
                + "grov,grove,grv:grv;"
                + "groves,grvs:grvs;"
                + "harb,harbor,harbr,hbr,hrbor:hbr;"
                + "harbors,hbrs:hbrs;"
                + "haven,hvn:hvn;"
                + "heights,ht,hts:hts;"
                + "highway,highwy,hiway,hiwy,hway,hwy:hwy;"
                + "hill,hl:hl;"
                + "hills,hls:hls;"
                + "hllw,hollow,hollows,holw,holws:holw;"
                + "inlet,inlt:inlt;"
                + "is,island,islnd:is;"
                + "iss,islands,islnds:iss;"
                + "isle,isles:isle;"
                + "jct,jction,jctn,junction,junctn,juncton:jct;"
                + "jcts,jctions,jctns,junctions,junctns,junctons:jcts;"
                + "key,ky:ky;"
                + "keys,kys:kys;"
                + "knl,knol,knoll:knl;"
                + "knls,knols,knolls:knls;"
                + "lk,lake:lk;"
                + "lks,lakes:lks;"
                + "land:land;"
                + "landing,lndg,lndng:lndg;"
                + "lgt,light:lgt;"
                + "lgts,lights:lgts;"
                + "loaf,lf:lf;"
                + "lock,lck:lck;"
                + "locks,lcks:lcks;"
                + "ldg,ldge,lodg,lodge:ldg;"
                + "lp,loop,loops:loop;"
                + "mall:mall;"
                + "mnr,manor:mnr;"
                + "mnrs,manors:mnrs;"
                + "mdw,meadow:mdw;"
                + "mdws,meadows,medows:mdws;"
                + "mews:mews;"
                + "mill,ml:ml;"
                + "mills,mls:mls;"
                + "mission,missn,mssn:msn;"
                + "motorway,mtwy:mtwy;"
                + "mnt,mt,mount:mt;"
                + "mntain,mntn,mountain,mountin,mtin,mtn:mtn;"
                + "mntns,mountains:mtns;"
                + "nck,neck:nck;"
                + "orch,orchard,orchrd:orch;"
                + "oval,ovl:oval;"
                + "opas,overpass:opas;"
                + "park,prk,parks,prks:park;"
                + "pkwy,parkway,parkways,parkwy,pkway,pkwys,pky:pkwy;"
                + "pass:pass;"
                + "passage,psge:psge;"
                + "path,paths:path;"
                + "pike,pikes:pike;"
                + "pine,pne:pne;"
                + "pines,pnes:pnes;"
                + "pl,place:pl;"
                + "plain,pln:pln;"
                + "plains,plns:plns;"
                + "plaza,plz,plza:plz;"
                + "point,pt:pt;"
                + "points,pts:pts;"
                + "port,prt:prt;"
                + "ports,prts:prts;"
                + "pr,prairie,prarie,prr:pr;"
                + "rad,radial,radiel,radl:radl;"
                + "ramp:ramp;"
                + "ranch,ranches,rnch,rnchs:rnch;"
                + "rapid,rpd:rpd;"
                + "rapids,rpds:rpds;"
                + "rest,rst:rst;"
                + "rdg,rdge,ridge:rdg;"
                + "rdgs,rdges,ridges:rdgs;"
                + "riv,river,rivr,rvr:riv;"
                + "rds,roads:rds;"
                + "route,rte:rte;"
                + "row:row;"
                + "rue:rue;"
                + "run:run;"
                + "shl,shoal:shl;"
                + "shls,shoals:shls;"
                + "shoar,shore,shr:shr;"
                + "shoars,shores,shrs:shrs;"
                + "skwy,skyway:skwy;"
                + "spg,spng,spring,sprng:spg;"
                + "spgs,spngs,springs,sprngs:spgs;"
                + "spur,spurs:spur;"
                + "sq,sqr,sqre,squ,square:sq;"
                + "sqrs,sqs,squares:sqs;"
                + "sta,station,statn,stn:sta;"
                + "stra,strav,strave,straven,stravenue,stravn,strvn,strvnue:stra;"
                + "stream,streme,strm:strm;"
                + "sts,streets:sts;"
                + "smt,sumit,sumitt,summit:smt;"
                + "ter,terr,terrace:ter;"
                + "throughway,trwy:trwy;"
                + "trace,traces,trce:trce;"
                + "track,tracks,trak,trk,trks:trak;"
                + "trafficway,trfy:trfy;"
                + "trail,trails,trl,trls,tr:trl;"
                + "trailer,trlr,trlrs:trlr;"
                + "tunel,tunl,tunls,tunnel,tunnels,tunnl:tunl;"
                + "trnpk,turnpike,turnpk,tpke:tpke;"
                + "underpass,upas:upas;"
                + "un,union:un;"
                + "uns,unions:uns;"
                + "valley,vally,vlly,vly:vly;"
                + "valleys,vlys:vllys:vlys;"
                + "vdct,via,viaduct,viadct:via;"
                + "view,vw:vw;"
                + "views,vws:vws;"
                + "vill,villag,village,villg,villiage,vlg:vlg;"
                + "villages,vlgs:vlgs;"
                + "ville,vl:vl;"
                + "vis,vist,vista,vst,vsta:vis;"
                + "walk,walks,wlk,wk:walk;"
                + "wall:wall;"
                + "way,wy:way;"
                + "ways,wys:ways;"
                + "well,wl:wl;"
                + "wells,wls:wls;";

        _commonStreetSuffixes = new HashSet<String>();
        String[] commonSuffixes = _mostCommonStreetSuffixes.split(",|\\:|;");
        _mostCommonStreetSuffixes.split(",|\\:|;");
        for (String currSuffix : commonSuffixes)
            _commonStreetSuffixes.add(currSuffix.toLowerCase().trim());

        _uncommonStreetSuffixes = new HashSet<String>();
        String[] uncommonSuffixes = _lessCommonStreetSuffixes.split(",|\\:|;");
        _mostCommonStreetSuffixes.split(",|\\:|;");
        for (String currSuffix : uncommonSuffixes)
            _uncommonStreetSuffixes.add(currSuffix.toLowerCase().trim());
    }

    public static boolean streetNumber1(String s) {
        return s.matches("\\d+\\w{0,3}") ||
                s.matches("\\d+-\\d+");
    }

    //public static boolean streetName1(String s) {        return s.matches("\\d{0,3}?i:\\w+");    }
    public static boolean streetName1(String s) {
        return s.matches("\\w+") &&
                !_standard_prefixes.contains(s.toLowerCase());
    }

    public static boolean streetName2(String s, String t) {
        if(s.equalsIgnoreCase("#") && t.matches("\\d{2,3}"))
            return true;

        return s.toLowerCase().matches("[a-z]+") &&
                t.matches("\\w+") &&
                ((s.length() >= 2) || !_directionals.contains(s.toLowerCase())) &&   //2205 NE North Valley Rd
                !_directionals.contains(t.toLowerCase()) &&
                !_commonStreetSuffixes.contains(t.toLowerCase()) &&
                !state1(t) &&
                !t.equalsIgnoreCase("county") &&
                !zip1(t);
    }


    public static boolean streetName3(String s, String t, String u) {
        return (s.equalsIgnoreCase("state") && t.equalsIgnoreCase("route") && u.matches("\\d+")) ||
                (t.equalsIgnoreCase("and") && streetName1(s) && streetName1(t)) ||
                (!streetSuffix1(t) && s.toLowerCase().matches("[a-z]{1,8}") && streetName2(t, u)) ||
                (s.toLowerCase().matches("[a-z]+") && t.toLowerCase().matches("[a-z]+") && u.toLowerCase().matches("[a-z]+") && !_commonStreetSuffixes.contains(u));
    }

    public static boolean city1(String s) {
        return s.toLowerCase().matches("[a-z]+") &&
                !state1(s) &&
                !_commonStreetSuffixes.contains(s.toLowerCase()) &&
                (!s.equalsIgnoreCase("county")) &&
                (!s.equalsIgnoreCase("township"));
    }

    public static boolean city2(String s, String t) {
        return s.toLowerCase().matches("[a-z]+") &&
                t.toLowerCase().matches("[a-z]+") &&
                !state2(s, t) &&
                !t.equalsIgnoreCase("county") &&
                !_commonStreetSuffixes.contains(s.toLowerCase()) &&
                !_commonStreetSuffixes.contains(t.toLowerCase()) &&
                !state1(t);
    }

    public static boolean city3(String s, String t, String u) {
        return (directional1(s) && city2(t, u)) ||
                (u.toLowerCase().contains("city") && city2(s, t)) ||
                (u.toLowerCase().contains("ranch") && city2(s, t));
    }

    public static boolean zip1(String s) {
        return s.matches("\\d{5}") || s.matches("\\d{5}\\-\\d{4}");
    }

    public static boolean zip2(String s, String t) {
        return s.matches("\\d{5}") &&
                t.matches("\\d{4}");
    }

    public static boolean state1(String s) {
        return _states.contains(s.toLowerCase());
    }

    public static boolean state2(String s, String t) {
        return _states.contains(s.toLowerCase() + " " + t);
    }

    public static boolean streetSuffix1(String s) {
        return _commonStreetSuffixes.contains(s.toLowerCase()) ||
                _uncommonStreetSuffixes.contains(s.toLowerCase());
    }

    public static boolean directional1(String s) {
        return _directionals.contains(s.toLowerCase());
    }

    public static boolean directional2(String s, String t) {
        return _multidirectionalStarts.contains(s.toLowerCase()) &&
                _multidirectionalEnds.contains(t.toLowerCase());
    }

    public static boolean comma1(String s) {
        return s.equals(",");
    }

    public static boolean pre_unit1(String s) {
        return s.toLowerCase().matches("[a-z]{0,2}\\d+[a-z]{0,2}") &&
                post_unit1(s);
    }

    public static boolean pre_unit2(String s, String t) {
        return post_unit2(s, t);
    }

    public static boolean post_unit1(String s) {
        return !streetSuffix1(s) &&
                ((s.length() <= 1) || !directional1(s)) &&
                s.toLowerCase().matches("(unit|ste|#)[a-z0-9]{1,9}-?[a-z0-9]{0,2}");
    }

    public static boolean post_unit2(String s, String t) {
        s = s.toLowerCase();
        return (_unitDescriptors.contains(s) || _unitDescriptors.contains(s.replaceAll(":|#", ""))) &&
                post_unit1(t);
    }

    public static boolean county2(String s, String t) {
        return s.toLowerCase().matches("[a-z]+") && t.equalsIgnoreCase("county");
    }

    public static boolean county3(String s, String t, String u) {
        return s.toLowerCase().matches("[a-z]+") && t.toLowerCase().matches("[a-z]+") && u.equalsIgnoreCase("county");
    }
}
