/**
 * Created by Angzhi on 11/11/2016.
 */

window.onload = function () {
    var container = document.getElementById('container');
    var start = document.getElementById('start');
    var gameRestart = document.getElementById('gameRestart');

    var ul = container.getElementsByTagName('ul');
    var p = container.getElementsByTagName('p');
    var li = container.getElementsByTagName('li');
    var scoreTag = document.getElementById('score');
    var status = document.getElementById('status');
    var life = document.getElementById('life');

    var music = document.getElementById('music');
    var scoreChange = 0;
    var combo = 0;
    var maxCombo = 0;
    var miss = 0;
    var bad = 0;
    var good = 0;
    var great = 0;
    var perfect = 0;
    var maxScore = 100; //TODO: is it really 100?
    var speed = 7;
    var timer = 0;
    var ended = 0;

    // create the four uls that acts as columns for the four arrows moving up
    for (var i = 0; i < 4; i++) {
        var ulChange = document.createElement('ul');
        container.appendChild(ulChange);
    }
    start.onclick = play;

    function colorRandom() {

        return "#" + ("00000" + ((Math.random() * 16777215 + 0.5) >> 0).toString(16)).slice(-6);
    }

    function generate(column) {
        var liChange = document.createElement('li');
        liChange.style.background = "-webkit-linear-gradient( top,transparent," + colorRandom() + ")";
        ul[column].appendChild(liChange);
        move(liChange/*, function () {
         if (li.length >= 20) {
         EndGame();
         }
         }*/);
    }

    // start game
    function play() {
        music.play();
        start.style.display = "none";
        status.innerHTML = "";
        var noteFile = [{"column":1,"time":190},{"column":1,"time":430},{"column":1,"time":670},{"column":1,"time":910},{"column":2,"time":1030},{"column":1,"time":1150},{"column":1,"time":1390},{"column":1,"time":1630},{"column":1,"time":1870},{"column":3,"time":1990},{"column":1,"time":2110},{"column":1,"time":2350},{"column":1,"time":2590},{"column":1,"time":2830},{"column":2,"time":2950},{"column":1,"time":3070},{"column":1,"time":3310},{"column":1,"time":3550},{"column":2,"time":3790},{"column":4,"time":4030},{"column":4,"time":4270},{"column":4,"time":4510},{"column":4,"time":4750},{"column":3,"time":4870},{"column":4,"time":4990},{"column":4,"time":5230},{"column":4,"time":5470},{"column":4,"time":5710},{"column":2,"time":5830},{"column":4,"time":5950},{"column":4,"time":6190},{"column":4,"time":6430},{"column":4,"time":6670},{"column":3,"time":6790},{"column":2,"time":6910},{"column":1,"time":7030},{"column":2,"time":7150},{"column":3,"time":7270},{"column":4,"time":7390},{"column":4,"time":7630},{"column":3,"time":7689},{"column":2,"time":7749},{"column":1,"time":7809},{"column":4,"time":7869},{"column":3,"time":7989},{"column":4,"time":8109},{"column":2,"time":8229},{"column":1,"time":8349},{"column":2,"time":8469},{"column":3,"time":8589},{"column":1,"time":8709},{"column":4,"time":8829},{"column":3,"time":8949},{"column":2,"time":9069},{"column":3,"time":9189},{"column":4,"time":9309},{"column":1,"time":9429},{"column":3,"time":9549},{"column":2,"time":9669},{"column":1,"time":9789},{"column":3,"time":9909},{"column":4,"time":10029},{"column":2,"time":10149},{"column":3,"time":10269},{"column":1,"time":10389},{"column":2,"time":10509},{"column":4,"time":10629},{"column":3,"time":10749},{"column":2,"time":10869},{"column":4,"time":10989},{"column":1,"time":11109},{"column":2,"time":11229},{"column":3,"time":11349},{"column":4,"time":11469},{"column":2,"time":11589},{"column":1,"time":11709},{"column":3,"time":11829},{"column":4,"time":11949},{"column":2,"time":12069},{"column":3,"time":12189},{"column":1,"time":12309},{"column":4,"time":12429},{"column":2,"time":12549},{"column":3,"time":12669},{"column":2,"time":12789},{"column":4,"time":12909},{"column":1,"time":13029},{"column":2,"time":13149},{"column":3,"time":13269},{"column":1,"time":13389},{"column":4,"time":13449},{"column":2,"time":13509},{"column":4,"time":13569},{"column":1,"time":13629},{"column":3,"time":13749},{"column":4,"time":13869},{"column":2,"time":13989},{"column":1,"time":14109},{"column":3,"time":14229},{"column":2,"time":14349},{"column":4,"time":14469},{"column":1,"time":14589},{"column":2,"time":14709},{"column":3,"time":14829},{"column":1,"time":14949},{"column":3,"time":15069},{"column":4,"time":15189},{"column":1,"time":15309},{"column":2,"time":15369},{"column":3,"time":15429},{"column":4,"time":15489},{"column":1,"time":15549},{"column":2,"time":15549},{"column":4,"time":15669},{"column":1,"time":15789},{"column":3,"time":15789},{"column":2,"time":15909},{"column":1,"time":16029},{"column":4,"time":16029},{"column":3,"time":16149},{"column":1,"time":16269},{"column":2,"time":16269},{"column":3,"time":16389},{"column":4,"time":16389},{"column":1,"time":16509},{"column":2,"time":16509},{"column":4,"time":16629},{"column":2,"time":16749},{"column":3,"time":16749},{"column":1,"time":16869},{"column":3,"time":16989},{"column":4,"time":16989},{"column":2,"time":17109},{"column":3,"time":17229},{"column":4,"time":17229},{"column":1,"time":17349},{"column":2,"time":17349},{"column":3,"time":17469},{"column":4,"time":17469},{"column":2,"time":17589},{"column":1,"time":17709},{"column":3,"time":17709},{"column":2,"time":17829},{"column":1,"time":17949},{"column":4,"time":17949},{"column":2,"time":18069},{"column":3,"time":18189},{"column":4,"time":18189},{"column":1,"time":18309},{"column":2,"time":18309},{"column":3,"time":18429},{"column":4,"time":18429},{"column":1,"time":18549},{"column":2,"time":18669},{"column":3,"time":18669},{"column":4,"time":18789},{"column":2,"time":18909},{"column":3,"time":18909},{"column":4,"time":19029},{"column":1,"time":19149},{"column":2,"time":19149},{"column":3,"time":19209},{"column":2,"time":19269},{"column":4,"time":19269},{"column":3,"time":19329},{"column":1,"time":19389},{"column":2,"time":19389},{"column":4,"time":19509},{"column":2,"time":19629},{"column":3,"time":19629},{"column":1,"time":19749},{"column":3,"time":19869},{"column":4,"time":19869},{"column":2,"time":19989},{"column":3,"time":20109},{"column":4,"time":20109},{"column":1,"time":20229},{"column":2,"time":20229},{"column":3,"time":20349},{"column":4,"time":20349},{"column":1,"time":20469},{"column":2,"time":20589},{"column":3,"time":20589},{"column":4,"time":20709},{"column":1,"time":20829},{"column":2,"time":20829},{"column":3,"time":20949},{"column":1,"time":21069},{"column":2,"time":21069},{"column":3,"time":21189},{"column":4,"time":21189},{"column":1,"time":21309},{"column":2,"time":21309},{"column":3,"time":21429},{"column":2,"time":21549},{"column":4,"time":21549},{"column":1,"time":21669},{"column":2,"time":21789},{"column":4,"time":21789},{"column":3,"time":21909},{"column":1,"time":22029},{"column":2,"time":22029},{"column":3,"time":22150},{"column":4,"time":22150},{"column":1,"time":22270},{"column":2,"time":22270},{"column":1,"time":22389},{"column":2,"time":22389},{"column":1,"time":22509},{"column":2,"time":22509},{"column":1,"time":22629},{"column":2,"time":22629},{"column":1,"time":22749},{"column":2,"time":22749},{"column":1,"time":22989},{"column":2,"time":23049},{"column":3,"time":23109},{"column":4,"time":23169},{"column":1,"time":23229},{"column":4,"time":23289},{"column":2,"time":23349},{"column":3,"time":23409},{"column":1,"time":23469},{"column":2,"time":23529},{"column":3,"time":23589},{"column":4,"time":23649},{"column":3,"time":23709},{"column":2,"time":23769},{"column":1,"time":23829},{"column":4,"time":23889},{"column":2,"time":23949},{"column":3,"time":24009},{"column":2,"time":24069},{"column":4,"time":24129},{"column":1,"time":24189},{"column":2,"time":24249},{"column":3,"time":24309},{"column":2,"time":24369},{"column":4,"time":24429},{"column":3,"time":24489},{"column":2,"time":24549},{"column":1,"time":24609},{"column":4,"time":24669},{"column":3,"time":24729},{"column":2,"time":24789},{"column":3,"time":24849},{"column":4,"time":24909},{"column":1,"time":24969},{"column":2,"time":25029},{"column":3,"time":25089},{"column":2,"time":25149},{"column":1,"time":25209},{"column":2,"time":25269},{"column":4,"time":25329},{"column":3,"time":25389},{"column":2,"time":25449},{"column":3,"time":25509},{"column":1,"time":25569},{"column":2,"time":25629},{"column":4,"time":25689},{"column":3,"time":25749},{"column":2,"time":25809},{"column":3,"time":25869},{"column":1,"time":25929},{"column":2,"time":25989},{"column":4,"time":26049},{"column":3,"time":26109},{"column":2,"time":26169},{"column":1,"time":26229},{"column":2,"time":26289},{"column":3,"time":26349},{"column":4,"time":26409},{"column":2,"time":26469},{"column":1,"time":26529},{"column":3,"time":26589},{"column":4,"time":26649},{"column":2,"time":26709},{"column":3,"time":26769},{"column":1,"time":26829},{"column":4,"time":26889},{"column":2,"time":26949},{"column":1,"time":27009},{"column":4,"time":27069},{"column":3,"time":27129},{"column":2,"time":27189},{"column":4,"time":27249},{"column":1,"time":27309},{"column":2,"time":27369},{"column":4,"time":27429},{"column":3,"time":27489},{"column":2,"time":27549},{"column":1,"time":27609},{"column":4,"time":27669},{"column":1,"time":27729},{"column":2,"time":27789},{"column":3,"time":27849},{"column":1,"time":27909},{"column":4,"time":27969},{"column":2,"time":28029},{"column":3,"time":28089},{"column":1,"time":28149},{"column":4,"time":28209},{"column":2,"time":28269},{"column":3,"time":28329},{"column":4,"time":28389},{"column":1,"time":28449},{"column":2,"time":28509},{"column":3,"time":28569},{"column":1,"time":28629},{"column":2,"time":28689},{"column":4,"time":28749},{"column":3,"time":28809},{"column":2,"time":28869},{"column":4,"time":28929},{"column":1,"time":28989},{"column":2,"time":29049},{"column":4,"time":29109},{"column":3,"time":29169},{"column":2,"time":29229},{"column":1,"time":29289},{"column":4,"time":29349},{"column":2,"time":29409},{"column":4,"time":29469},{"column":1,"time":29529},{"column":2,"time":29589},{"column":4,"time":29649},{"column":3,"time":29709},{"column":2,"time":29769},{"column":4,"time":29829},{"column":1,"time":29889},{"column":2,"time":29949},{"column":4,"time":30009},{"column":3,"time":30069},{"column":2,"time":30129},{"column":3,"time":30189},{"column":1,"time":30249},{"column":2,"time":30309},{"column":4,"time":30369},{"column":3,"time":30429},{"column":2,"time":30489},{"column":1,"time":30549},{"column":3,"time":30609},{"column":2,"time":30669},{"column":4,"time":30729},{"column":1,"time":30789},{"column":2,"time":30849},{"column":3,"time":30909},{"column":4,"time":30969},{"column":2,"time":31029},{"column":3,"time":31089},{"column":1,"time":31149},{"column":4,"time":31209},{"column":3,"time":31269},{"column":2,"time":31329},{"column":4,"time":31389},{"column":1,"time":31449},{"column":2,"time":31509},{"column":4,"time":31569},{"column":2,"time":31629},{"column":1,"time":31689},{"column":3,"time":31749},{"column":4,"time":31809},{"column":2,"time":31869},{"column":3,"time":31929},{"column":1,"time":31989},{"column":2,"time":32049},{"column":1,"time":32109},{"column":4,"time":32169},{"column":3,"time":32229},{"column":2,"time":32289},{"column":3,"time":32349},{"column":4,"time":32409},{"column":1,"time":32469},{"column":2,"time":32529},{"column":3,"time":32589},{"column":2,"time":32649},{"column":1,"time":32709},{"column":4,"time":32769},{"column":1,"time":32829},{"column":2,"time":32889},{"column":4,"time":32949},{"column":3,"time":33009},{"column":2,"time":33069},{"column":1,"time":33129},{"column":3,"time":33189},{"column":2,"time":33249},{"column":4,"time":33309},{"column":1,"time":33369},{"column":2,"time":33429},{"column":3,"time":33489},{"column":2,"time":33549},{"column":4,"time":33609},{"column":3,"time":33669},{"column":2,"time":33729},{"column":1,"time":33789},{"column":3,"time":33849},{"column":1,"time":33909},{"column":4,"time":33969},{"column":2,"time":34029},{"column":3,"time":34089},{"column":1,"time":34149},{"column":4,"time":34209},{"column":2,"time":34269},{"column":3,"time":34329},{"column":1,"time":34389},{"column":4,"time":34449},{"column":2,"time":34509},{"column":3,"time":34569},{"column":1,"time":34629},{"column":4,"time":34689},{"column":3,"time":34749},{"column":2,"time":34809},{"column":4,"time":34869},{"column":1,"time":34929},{"column":3,"time":34989},{"column":2,"time":35049},{"column":4,"time":35109},{"column":1,"time":35169},{"column":2,"time":35229},{"column":3,"time":35289},{"column":4,"time":35349},{"column":2,"time":35409},{"column":3,"time":35469},{"column":1,"time":35529},{"column":4,"time":35589},{"column":2,"time":35649},{"column":3,"time":35709},{"column":1,"time":35769},{"column":4,"time":35829},{"column":3,"time":35889},{"column":2,"time":35949},{"column":3,"time":36009},{"column":4,"time":36069},{"column":2,"time":36129},{"column":3,"time":36189},{"column":1,"time":36249},{"column":4,"time":36309},{"column":2,"time":36369},{"column":3,"time":36429},{"column":4,"time":36489},{"column":3,"time":36549},{"column":2,"time":36609},{"column":1,"time":36669},{"column":2,"time":36729},{"column":3,"time":36789},{"column":4,"time":36849},{"column":1,"time":36909},{"column":2,"time":36969},{"column":3,"time":37029},{"column":4,"time":37089},{"column":1,"time":37149},{"column":2,"time":37209},{"column":3,"time":37269},{"column":4,"time":37329},{"column":1,"time":37389},{"column":2,"time":37449},{"column":3,"time":37510},{"column":1,"time":37570},{"column":4,"time":37630},{"column":3,"time":37690},{"column":2,"time":37750},{"column":1,"time":37810},{"column":4,"time":37870},{"column":3,"time":37930},{"column":2,"time":37990},{"column":1,"time":38050},{"column":4,"time":38110},{"column":3,"time":38170},{"column":2,"time":38230},{"column":1,"time":38290},{"column":4,"time":38350},{"column":3,"time":38410},{"column":2,"time":38470},{"column":3,"time":38530},{"column":1,"time":38590},{"column":2,"time":38590},{"column":4,"time":38590},{"column":1,"time":40510},{"column":3,"time":40510},{"column":4,"time":40510},{"column":1,"time":42430},{"column":4,"time":42430},{"column":3,"time":42490},{"column":1,"time":42550},{"column":3,"time":42610},{"column":1,"time":42670},{"column":3,"time":42730},{"column":1,"time":42790},{"column":3,"time":42850},{"column":2,"time":42910},{"column":3,"time":43390},{"column":4,"time":43390},{"column":1,"time":43450},{"column":4,"time":43510},{"column":1,"time":43570},{"column":4,"time":43630},{"column":1,"time":43690},{"column":4,"time":43750},{"column":1,"time":43810},{"column":3,"time":43870},{"column":2,"time":44110},{"column":1,"time":44350},{"column":2,"time":44410},{"column":1,"time":44470},{"column":2,"time":44530},{"column":1,"time":44590},{"column":4,"time":44830},{"column":3,"time":44890},{"column":4,"time":44950},{"column":3,"time":45010},{"column":4,"time":45070},{"column":1,"time":45310},{"column":2,"time":45370},{"column":3,"time":45430},{"column":4,"time":45490},{"column":1,"time":45550},{"column":2,"time":45610},{"column":3,"time":45670},{"column":4,"time":45730},{"column":1,"time":45790},{"column":2,"time":45850},{"column":3,"time":45910},{"column":4,"time":45970},{"column":1,"time":46030},{"column":2,"time":46090},{"column":3,"time":46150},{"column":4,"time":46210},{"column":1,"time":46270},{"column":3,"time":46330},{"column":2,"time":46390},{"column":4,"time":46450},{"column":3,"time":46510},{"column":1,"time":46570},{"column":2,"time":46630},{"column":4,"time":46690},{"column":3,"time":46750},{"column":2,"time":46810},{"column":4,"time":46870},{"column":1,"time":46930},{"column":2,"time":46990},{"column":4,"time":47050},{"column":3,"time":47110},{"column":2,"time":47170},{"column":4,"time":47230},{"column":1,"time":47290},{"column":2,"time":47350},{"column":3,"time":47410},{"column":2,"time":47470},{"column":4,"time":47530},{"column":1,"time":47590},{"column":3,"time":47650},{"column":4,"time":47710},{"column":2,"time":47770},{"column":3,"time":47830},{"column":1,"time":47890},{"column":4,"time":47950},{"column":3,"time":48010},{"column":2,"time":48070},{"column":4,"time":48130},{"column":1,"time":48190},{"column":2,"time":48250},{"column":3,"time":48310},{"column":4,"time":48370},{"column":2,"time":48430},{"column":3,"time":48490},{"column":1,"time":48550},{"column":4,"time":48610},{"column":2,"time":48670},{"column":3,"time":48730},{"column":1,"time":48790},{"column":2,"time":48850},{"column":4,"time":48910},{"column":3,"time":48970},{"column":2,"time":49030},{"column":1,"time":49090},{"column":4,"time":49150},{"column":3,"time":49210},{"column":2,"time":49270},{"column":4,"time":49330},{"column":3,"time":49390},{"column":2,"time":49450},{"column":1,"time":49510},{"column":3,"time":49570},{"column":4,"time":49630},{"column":2,"time":49690},{"column":3,"time":49750},{"column":1,"time":49810},{"column":4,"time":49870},{"column":2,"time":49930},{"column":3,"time":49990},{"column":4,"time":50050},{"column":1,"time":50110},{"column":2,"time":50110},{"column":3,"time":50230},{"column":2,"time":50350},{"column":4,"time":50350},{"column":1,"time":50470},{"column":2,"time":50590},{"column":3,"time":50590},{"column":4,"time":50710},{"column":1,"time":50830},{"column":2,"time":50830},{"column":4,"time":50950},{"column":2,"time":51070},{"column":3,"time":51070},{"column":1,"time":51190},{"column":3,"time":51310},{"column":4,"time":51310},{"column":2,"time":51430},{"column":1,"time":51550},{"column":2,"time":51670},{"column":3,"time":51730},{"column":4,"time":51790},{"column":4,"time":51910},{"column":3,"time":51970},{"column":2,"time":52030},{"column":4,"time":52150},{"column":2,"time":52270},{"column":3,"time":52270},{"column":1,"time":52390},{"column":3,"time":52510},{"column":4,"time":52510},{"column":2,"time":52630},{"column":1,"time":52750},{"column":3,"time":52750},{"column":2,"time":52870},{"column":3,"time":52990},{"column":4,"time":52990},{"column":2,"time":53110},{"column":1,"time":53230},{"column":4,"time":53230},{"column":2,"time":53350},{"column":1,"time":53470},{"column":3,"time":53530},{"column":1,"time":53590},{"column":3,"time":53650},{"column":1,"time":53710},{"column":4,"time":53830},{"column":1,"time":53950},{"column":4,"time":54010},{"column":3,"time":54070},{"column":1,"time":54130},{"column":2,"time":54190},{"column":4,"time":54250},{"column":3,"time":54310},{"column":2,"time":54370},{"column":1,"time":54430},{"column":4,"time":54490},{"column":3,"time":54550},{"column":1,"time":54610},{"column":2,"time":54670},{"column":4,"time":54730},{"column":3,"time":54790},{"column":2,"time":54850},{"column":4,"time":54910},{"column":1,"time":54970},{"column":2,"time":55030},{"column":3,"time":55090},{"column":2,"time":55150},{"column":1,"time":55210},{"column":4,"time":55270},{"column":3,"time":55330},{"column":2,"time":55390},{"column":4,"time":55450},{"column":1,"time":55510},{"column":2,"time":55570},{"column":4,"time":55630},{"column":3,"time":55690},{"column":2,"time":55750},{"column":1,"time":55810},{"column":4,"time":55870},{"column":3,"time":55930},{"column":1,"time":55990},{"column":2,"time":56050},{"column":4,"time":56110},{"column":2,"time":56170},{"column":3,"time":56230},{"column":1,"time":56290},{"column":4,"time":56350},{"column":2,"time":56410},{"column":3,"time":56470},{"column":1,"time":56530},{"column":2,"time":56590},{"column":4,"time":56650},{"column":3,"time":56710},{"column":1,"time":56770},{"column":2,"time":56830},{"column":4,"time":56890},{"column":3,"time":56950},{"column":2,"time":57010},{"column":1,"time":57070},{"column":2,"time":57130},{"column":4,"time":57190},{"column":3,"time":57250},{"column":4,"time":57310},{"column":2,"time":57370},{"column":3,"time":57430},{"column":1,"time":57490},{"column":2,"time":57550},{"column":4,"time":57610},{"column":3,"time":57670},{"column":2,"time":57730},{"column":4,"time":57790},{"column":1,"time":57850},{"column":2,"time":57910},{"column":3,"time":57970},{"column":1,"time":58030},{"column":4,"time":58090},{"column":2,"time":58150},{"column":3,"time":58210},{"column":1,"time":58270},{"column":2,"time":58330},{"column":4,"time":58390},{"column":3,"time":58450},{"column":1,"time":58510},{"column":4,"time":58570},{"column":2,"time":58630},{"column":3,"time":58690},{"column":1,"time":58750},{"column":4,"time":58810},{"column":2,"time":58870},{"column":3,"time":58930},{"column":4,"time":58990},{"column":1,"time":59050},{"column":2,"time":59110},{"column":3,"time":59170},{"column":2,"time":59230},{"column":4,"time":59290},{"column":3,"time":59350},{"column":1,"time":59410},{"column":2,"time":59470},{"column":4,"time":59530},{"column":3,"time":59590},{"column":2,"time":59650},{"column":1,"time":59710},{"column":4,"time":59710},{"column":2,"time":59950},{"column":3,"time":59950},{"column":1,"time":60190},{"column":4,"time":60190},{"column":2,"time":60430},{"column":3,"time":60430},{"column":1,"time":60670},{"column":2,"time":60670},{"column":3,"time":60910},{"column":4,"time":60910},{"column":1,"time":61150},{"column":2,"time":61150},{"column":3,"time":61390},{"column":4,"time":61390},{"column":1,"time":61630},{"column":3,"time":61750},{"column":1,"time":61870},{"column":3,"time":61990},{"column":1,"time":62110},{"column":2,"time":62350},{"column":4,"time":62590},{"column":1,"time":62650},{"column":4,"time":62710},{"column":1,"time":62770},{"column":4,"time":62830},{"column":1,"time":62890},{"column":4,"time":62950},{"column":1,"time":63010},{"column":4,"time":63070},{"column":4,"time":63310},{"column":4,"time":63430},{"column":1,"time":63550},{"column":3,"time":63610},{"column":2,"time":63670},{"column":4,"time":63730},{"column":1,"time":63790},{"column":3,"time":63850},{"column":1,"time":63910},{"column":2,"time":63970},{"column":4,"time":64030},{"column":3,"time":64090},{"column":1,"time":64150},{"column":2,"time":64210},{"column":4,"time":64270},{"column":3,"time":64330},{"column":2,"time":64390},{"column":1,"time":64450},{"column":4,"time":64510},{"column":3,"time":64570},{"column":4,"time":64630},{"column":1,"time":64690},{"column":2,"time":64750},{"column":3,"time":64810},{"column":1,"time":64870},{"column":2,"time":64930},{"column":4,"time":64990},{"column":3,"time":65050},{"column":1,"time":65110},{"column":2,"time":65170},{"column":3,"time":65230},{"column":1,"time":65290},{"column":4,"time":65350},{"column":3,"time":65410},{"column":4,"time":65470},{"column":2,"time":65530},{"column":3,"time":65590},{"column":4,"time":65650},{"column":2,"time":65710},{"column":1,"time":65770},{"column":4,"time":65830},{"column":3,"time":65890},{"column":2,"time":65950},{"column":4,"time":66010},{"column":3,"time":66070},{"column":2,"time":66130},{"column":3,"time":66190},{"column":4,"time":66250},{"column":1,"time":66310},{"column":2,"time":66370},{"column":1,"time":66430},{"column":4,"time":66490},{"column":2,"time":66550},{"column":3,"time":66610},{"column":1,"time":66670},{"column":4,"time":66730},{"column":2,"time":66790},{"column":3,"time":66850},{"column":4,"time":66910},{"column":1,"time":66970},{"column":2,"time":67030},{"column":3,"time":67090},{"column":1,"time":67150},{"column":4,"time":67210},{"column":1,"time":67270},{"column":4,"time":67330},{"column":1,"time":67390},{"column":2,"time":67450},{"column":4,"time":67510},{"column":3,"time":67570},{"column":1,"time":67630},{"column":2,"time":67690},{"column":3,"time":67750},{"column":4,"time":67810},{"column":2,"time":67870},{"column":1,"time":67930},{"column":3,"time":67990},{"column":4,"time":68050},{"column":2,"time":68110},{"column":1,"time":68170},{"column":3,"time":68230},{"column":4,"time":68290},{"column":3,"time":68350},{"column":2,"time":68410},{"column":1,"time":68470},{"column":3,"time":68530},{"column":2,"time":68590},{"column":4,"time":68650},{"column":3,"time":68710},{"column":1,"time":68770},{"column":3,"time":68830},{"column":2,"time":68890},{"column":4,"time":68950},{"column":3,"time":69010},{"column":4,"time":69070},{"column":3,"time":69130},{"column":1,"time":69190},{"column":2,"time":69250},{"column":1,"time":69310},{"column":2,"time":69370},{"column":3,"time":69430},{"column":4,"time":69490},{"column":2,"time":69550},{"column":1,"time":69610},{"column":2,"time":69670},{"column":3,"time":69730},{"column":4,"time":69790},{"column":3,"time":69850},{"column":2,"time":69910},{"column":1,"time":69970},{"column":2,"time":70030},{"column":4,"time":70090},{"column":3,"time":70150},{"column":2,"time":70210},{"column":4,"time":70270},{"column":1,"time":70330},{"column":4,"time":70390},{"column":1,"time":70450},{"column":4,"time":70510},{"column":1,"time":70570},{"column":4,"time":70630},{"column":1,"time":70690},{"column":4,"time":70750},{"column":1,"time":70810},{"column":4,"time":70870},{"column":1,"time":70930},{"column":4,"time":70990},{"column":1,"time":71050},{"column":4,"time":71110},{"column":1,"time":71170},{"column":4,"time":71230},{"column":2,"time":71350},{"column":1,"time":71470},{"column":4,"time":71590},{"column":3,"time":71710},{"column":2,"time":71830},{"column":1,"time":71950},{"column":4,"time":72070},{"column":2,"time":72190},{"column":3,"time":72310},{"column":1,"time":72430},{"column":2,"time":72550},{"column":4,"time":72670},{"column":3,"time":72790},{"column":1,"time":72910},{"column":2,"time":73030},{"column":3,"time":73090},{"column":4,"time":73150},{"column":2,"time":73270},{"column":3,"time":73390},{"column":1,"time":73510},{"column":2,"time":73630},{"column":4,"time":73750},{"column":3,"time":73870},{"column":1,"time":73990},{"column":2,"time":74110},{"column":4,"time":74230},{"column":3,"time":74350},{"column":1,"time":74470},{"column":2,"time":74590},{"column":1,"time":74710},{"column":3,"time":74830},{"column":4,"time":74950},{"column":3,"time":75010},{"column":2,"time":75070},{"column":1,"time":75190},{"column":4,"time":75310},{"column":3,"time":75430},{"column":2,"time":75550},{"column":4,"time":75670},{"column":1,"time":75790},{"column":2,"time":75910},{"column":3,"time":76030},{"column":4,"time":76150},{"column":2,"time":76270},{"column":3,"time":76390},{"column":1,"time":76510},{"column":2,"time":76630},{"column":4,"time":76750},{"column":3,"time":76870},{"column":1,"time":76990},{"column":4,"time":77050},{"column":3,"time":77110},{"column":2,"time":77170},{"column":4,"time":77230},{"column":1,"time":77290},{"column":2,"time":77350},{"column":3,"time":77410},{"column":4,"time":77470},{"column":2,"time":77530},{"column":1,"time":77590},{"column":4,"time":77650},{"column":3,"time":77710},{"column":2,"time":77770},{"column":4,"time":77830},{"column":3,"time":77890},{"column":1,"time":77950},{"column":2,"time":78070},{"column":4,"time":78190},{"column":3,"time":78310},{"column":1,"time":78430},{"column":2,"time":78550},{"column":3,"time":78610},{"column":4,"time":78670},{"column":4,"time":78790},{"column":3,"time":78850},{"column":2,"time":78910},{"column":4,"time":78970},{"column":1,"time":79030},{"column":2,"time":79090},{"column":4,"time":79150},{"column":2,"time":79210},{"column":3,"time":79270},{"column":1,"time":79330},{"column":4,"time":79390},{"column":2,"time":79450},{"column":4,"time":79510},{"column":3,"time":79570},{"column":1,"time":79630},{"column":4,"time":79690},{"column":2,"time":79750},{"column":3,"time":79810},{"column":1,"time":79870},{"column":4,"time":79930},{"column":2,"time":79990},{"column":3,"time":80050},{"column":1,"time":80110},{"column":2,"time":80170},{"column":4,"time":80230},{"column":3,"time":80290},{"column":1,"time":80350},{"column":2,"time":80410},{"column":4,"time":80470},{"column":3,"time":80530},{"column":1,"time":80590},{"column":2,"time":80650},{"column":3,"time":80710},{"column":4,"time":80770},{"column":2,"time":80830},{"column":1,"time":80890},{"column":2,"time":80950},{"column":4,"time":81010},{"column":3,"time":81070},{"column":4,"time":81130},{"column":2,"time":81190},{"column":1,"time":81250},{"column":2,"time":81310},{"column":4,"time":81370},{"column":3,"time":81430},{"column":2,"time":81490},{"column":1,"time":81550},{"column":4,"time":81610},{"column":3,"time":81670},{"column":2,"time":81730},{"column":4,"time":81790},{"column":1,"time":81850},{"column":3,"time":81910},{"column":2,"time":81970},{"column":4,"time":82030},{"column":1,"time":82090},{"column":3,"time":82150},{"column":2,"time":82210},{"column":4,"time":82270},{"column":3,"time":82330},{"column":1,"time":82390},{"column":2,"time":82450},{"column":4,"time":82510},{"column":3,"time":82570},{"column":2,"time":82630},{"column":1,"time":82690},{"column":4,"time":82750},{"column":3,"time":82810},{"column":1,"time":82870},{"column":4,"time":82930},{"column":3,"time":82990},{"column":2,"time":83050},{"column":4,"time":83110},{"column":1,"time":83170},{"column":2,"time":83230},{"column":4,"time":83290},{"column":3,"time":83350},{"column":1,"time":83410},{"column":2,"time":83470},{"column":4,"time":83530},{"column":3,"time":83590},{"column":1,"time":83650},{"column":2,"time":83710},{"column":4,"time":83770},{"column":3,"time":83830},{"column":1,"time":83890},{"column":2,"time":83950},{"column":3,"time":84010},{"column":2,"time":84070},{"column":4,"time":84130},{"column":3,"time":84190},{"column":1,"time":84250},{"column":2,"time":84310},{"column":4,"time":84370},{"column":3,"time":84430},{"column":1,"time":84490},{"column":2,"time":84550},{"column":4,"time":84610},{"column":3,"time":84670},{"column":2,"time":84730},{"column":1,"time":84790},{"column":4,"time":84850},{"column":1,"time":84910},{"column":2,"time":84970},{"column":3,"time":85030},{"column":1,"time":85090},{"column":4,"time":85150},{"column":3,"time":85210},{"column":1,"time":85270},{"column":4,"time":85330},{"column":2,"time":85390},{"column":1,"time":85450},{"column":2,"time":85510},{"column":3,"time":85570},{"column":1,"time":85630},{"column":4,"time":85690},{"column":3,"time":85750},{"column":1,"time":85810},{"column":2,"time":85870},{"column":3,"time":85930},{"column":1,"time":85990},{"column":2,"time":86050},{"column":4,"time":86110},{"column":3,"time":86170},{"column":4,"time":86230},{"column":3,"time":86290},{"column":4,"time":86350},{"column":1,"time":86410},{"column":2,"time":86470},{"column":3,"time":86530},{"column":2,"time":86590},{"column":1,"time":86650},{"column":3,"time":86710},{"column":4,"time":86770},{"column":2,"time":86830},{"column":1,"time":86890},{"column":3,"time":86950},{"column":2,"time":87010},{"column":1,"time":87070},{"column":4,"time":87130},{"column":3,"time":87190},{"column":2,"time":87250},{"column":4,"time":87310},{"column":1,"time":87370},{"column":2,"time":87430},{"column":3,"time":87490},{"column":4,"time":87550},{"column":2,"time":87610},{"column":3,"time":87670},{"column":1,"time":87730},{"column":4,"time":87790},{"column":3,"time":87850},{"column":2,"time":87910},{"column":4,"time":87970},{"column":1,"time":88030},{"column":2,"time":88090},{"column":4,"time":88150},{"column":3,"time":88210},{"column":1,"time":88270},{"column":2,"time":88330},{"column":4,"time":88390},{"column":1,"time":88450},{"column":4,"time":88510},{"column":3,"time":88570},{"column":2,"time":88630},{"column":1,"time":88690},{"column":4,"time":88750},{"column":3,"time":88810},{"column":1,"time":88870},{"column":4,"time":88930},{"column":2,"time":88990},{"column":3,"time":89050},{"column":1,"time":89110},{"column":2,"time":89170},{"column":4,"time":89230},{"column":2,"time":89290},{"column":3,"time":89350},{"column":1,"time":89410},{"column":4,"time":89470},{"column":2,"time":89530},{"column":3,"time":89590},{"column":1,"time":89650},{"column":4,"time":89710},{"column":3,"time":89770},{"column":2,"time":89830},{"column":1,"time":89890},{"column":3,"time":89950},{"column":2,"time":90010},{"column":4,"time":90070},{"column":1,"time":90130},{"column":2,"time":90190},{"column":4,"time":90250},{"column":3,"time":90310},{"column":1,"time":90370},{"column":2,"time":90430},{"column":4,"time":90490},{"column":3,"time":90550},{"column":2,"time":90610},{"column":4,"time":90670},{"column":1,"time":90730},{"column":2,"time":90790},{"column":3,"time":90850},{"column":2,"time":90910},{"column":4,"time":90970},{"column":1,"time":91030},{"column":2,"time":91090},{"column":4,"time":91150},{"column":3,"time":91210},{"column":2,"time":91270},{"column":4,"time":91330},{"column":1,"time":91390},{"column":2,"time":91450},{"column":4,"time":91510},{"column":3,"time":91570},{"column":2,"time":91630},{"column":1,"time":91690},{"column":3,"time":91750},{"column":4,"time":91810},{"column":3,"time":91870},{"column":1,"time":91930},{"column":2,"time":91990},{"column":3,"time":92050},{"column":1,"time":92110},{"column":4,"time":92170},{"column":3,"time":92230},{"column":2,"time":92290},{"column":1,"time":92350},{"column":4,"time":92410},{"column":3,"time":92470},{"column":1,"time":92530},{"column":2,"time":92590},{"column":4,"time":92650},{"column":3,"time":92710},{"column":2,"time":92770},{"column":1,"time":92830},{"column":3,"time":92890},{"column":2,"time":92950},{"column":4,"time":93010},{"column":2,"time":93070},{"column":1,"time":93130},{"column":3,"time":93190},{"column":4,"time":93250},{"column":2,"time":93310},{"column":4,"time":93370},{"column":1,"time":93430},{"column":2,"time":93490},{"column":3,"time":93550},{"column":1,"time":93610},{"column":2,"time":93670},{"column":3,"time":93730},{"column":4,"time":93790},{"column":1,"time":94030},{"column":2,"time":94030},{"column":4,"time":94030}]
/*
        var noteFile = JSON.parse(string);
*/
        for (var n in noteFile) {
            var column = parseInt(noteFile[n].column);
            var time = Date.parse(noteFile[n].time);
            while (Date.now() < time) {
            }
            generate(column);
        }
    }

    music.addEventListener("ended", function () {
        music.currentTime = 0;
        ended = 1;
    });

    // end game
    function EndGame() {
        clearInterval(timer);
        for (var i = 0; i < li.length; i++) {
            clearTimeout(li[i].timeout);
        }
        for (i = 0; i < ul.length; i++) {
            ul[i].innerHTML = '';
        }
        scoreChange = 0;
        speed = 7;
    }

    // check remaining life
    function lifeCheck() {
        if (life.innerHTML == 0) {
            life.innerHTML = "Dead";
            status.innerHTML = " Score: F" + '<br />' + "Press F to restart";
            EndGame();
        }
    }

    // Core judgement functions
    function processKeyPress(n) {
        if (ended/*!ul[n].children.length*/) {
            if (scoreChange == maxScore) {
                status.innerHTML = " Score: AAA" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.99) {
                status.innerHTML = " Score:  AA" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.97) {
                status.innerHTML = " Score:  A+" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.92) {
                status.innerHTML = " Score:  A" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.90) {
                status.innerHTML = " Score:  A-" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.88) {
                status.innerHTML = " Score:  B+" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.82) {
                status.innerHTML = " Score:  B" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.80) {
                status.innerHTML = " Score:  B-" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.78) {
                status.innerHTML = " Score:  C+" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.72) {
                status.innerHTML = " Score:  C" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.70) {
                status.innerHTML = " Score:  C-" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.68) {
                status.innerHTML = " Score:  D+" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.62) {
                status.innerHTML = " Score:  D" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            else if (scoreChange >= maxScore * 0.60) {
                status.innerHTML = " Score:  D-" + '<br />' + "Combo: " + maxCombo + '<br />' + "Press F to restart";
            }
            EndGame();
            return;
        }
        var height = coll(ul[n].children[0], p[n]);

        console.log(typeof(height));
        console.log(height);

        if (height) {
            ul[n].removeChild(ul[n].children[0]);
            scoreTag.innerHTML = scoreChange;
        } else {
            status.innerHTML = "MISS";
            lifeCheck();
            ul[n].removeChild(ul[n].children[0]);
        }
    }

    function reload() {
        document.location.reload();
    }

    // mouse onclick events
    gameRestart.onclick = reload;

    // key down event
    document.onkeydown = function (ev) {
        var event = ev || event;

        // processKeyPress key press
        switch (event.keyCode) {
            // change these number for different keys, default qwop
            case 81: //Q
                processKeyPress(0);
                break;
            case 87: //W
                processKeyPress(1);
                break;
            case 79: //O
                processKeyPress(2);
                break;
            case 80: //P
                processKeyPress(3);
                break;
            case 70: //F for reload
                reload();
                break;
        }
    };

    // move arrows up
    function move(obj) {
        clearInterval(obj.timer);
        obj.timer = setInterval(function () {
            obj.style.top = obj.offsetTop + speed + 'px';
        }, 30);
    }

    // obtain note location
    function getPos(obj) {
        var left = 0;
        var top = 0;
        while (obj) {
            left += obj.offsetLeft;
            top += obj.offsetTop;
            obj = obj.offsetParent
        }
        return {'left': left, 'top': top};
    }

    // processKeyPress if scores
    function coll(arrow, receptor) {
        //var tapTime = Date.now() - noteFile.trueTime;
        var arrowL = getPos(arrow).left;
        var arrowR = getPos(arrow).left + arrow.offsetWidth;
        var arrowT = getPos(arrow).top;
        var arrowB = getPos(arrow).top + arrow.offsetHeight;
        // console.log("distance from the top:" + t1);
        var receptorL = getPos(receptor).left;
        var receptorR = getPos(receptor).left + receptor.offsetWidth;
        var receptorT = getPos(receptor).top;
        var receptorB = getPos(receptor).top + receptor.offsetHeight;

        if (arrowL > receptorR || arrowR < receptorL) {
            // status.innerHTML="WRONG!";
            return false;
        }
        console.log("distance from the left:" + arrowL);
        console.log("distance from the top:" + arrowB);

        if (arrowB >= 485 && arrowB <= 528) {
            status.innerHTML = "Perfect!";
            scoreChange += 2;
            perfect += 1;
            combo += 1;
            life += 2;
            return true;
        }
        else if (arrowB > 460 && arrowB < 560) {
            status.innerHTML = "Great!";
            scoreChange += 1;
            great += 1;
            combo += 1;
            life += 1;
            return true;
        }
        else if (arrowB > 440 && arrowB < 580) {
            status.innerHTML = "Good";
            good += 1;
            combo = 0;
        }
        else if (arrowB > 410 && arrowB < 600) {
            status.innerHTML = "Bad";
            bad += 1;
            combo = 0;
            scoreChange -= 1;
            life -= 5;
        }
        else {
            status.innerHTML = "MISS";
            miss += 1;
            combo = 0;
            scoreChange -= 2;
            life -= 10;
        }
        if (combo > maxCombo) {
            maxCombo = combo;
        }
    }
};