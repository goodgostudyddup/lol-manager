document.addEventListener('DOMContentLoaded', function() {
    let matchData = [];
    let totalKills = 0;
    let totalAssists = 0;
    let totalDeaths = 0;
    const urlParams = new URLSearchParams(window.location.search);
    const puuid = urlParams.get('puuid');

    if (puuid) {
        // 获取并显示最近10场比赛
        for (let i = 0; i < 10; i++) {
            fetch(`http://localhost:8080/Client/getProductsMatchHistoryBybegIndex?puuid=${puuid}&begIndex=${i}`)
                .then(response => response.json())
                .then(data => {
                    const gameId = data.games.games[0].gameId;
                    const game = data.games.games[0];
                    const participant = game.participants[0];
                    totalKills += participant.stats.kills;
                    totalAssists += participant.stats.assists;
                    totalDeaths += participant.stats.deaths;

                    matchData.push({
                        gameId: gameId,
                        kda: `${participant.stats.kills}/${participant.stats.deaths}/${participant.stats.assists}`,
                        goldEarned: participant.stats.goldEarned,
                        win: participant.stats.win,
                        championId: participant.championId,
                        gameCreationDate: new Date(game.gameCreation),
                        spells1: participant.spell1Id,
                        spells2: participant.spell2Id,
                        items: [
                            participant.stats.item0,
                            participant.stats.item1,
                            participant.stats.item2,
                            participant.stats.item3,
                            participant.stats.item4,
                            participant.stats.item5,
                            participant.stats.item6
                        ]
                    });

                    // 按游戏创建日期降序排序
                    matchData.sort((a, b) => b.gameCreationDate - a.gameCreationDate);

                    if (matchData.length === i + 1) {
                        const kdaRatio = totalDeaths === 0 ? '完美' : (((totalKills + totalAssists) / totalDeaths) * 3).toFixed(2);
                        document.querySelector('.avgkda p').textContent = kdaRatio;
                        displayMatches(matchData);
                    }
                })
                .catch(error => {
                    console.error('获取比赛历史时出错:', error);
                });
        }

        function displayMatches(matches) {
            const matchHistoryList = document.querySelector('#match-history-list');
            matchHistoryList.innerHTML = ''; // 清除现有比赛
            matches.forEach(match => {
                const gameInfoContainer = document.createElement('li');
                gameInfoContainer.className = 'game-info-container';
                gameInfoContainer.innerHTML = `
                    <div class="game-info">
                        <figure id="yxtx">
                            <img src="yxtx/${match.championId}.png" alt="英雄头像" class="avatar"> <!-- 使用 'yxtx' 文件夹 -->
                        </figure>
                        <div class="spells">
                            <div id="spells1">
                                <img src="jineng/${match.spells1}.png" alt="召唤师技能1"> <!-- 使用 'jineng' 文件夹 -->
                            </div>
                            <div id="spells2">
                                <img src="jineng/${match.spells2}.png" alt="召唤师技能2"> <!-- 使用 'jineng' 文件夹 -->
                            </div>
                        </div>
                        <div class="details">
                            <div class="stats">
                                <div id="kda">KDA: <span id="kda-value">${match.kda}</span></div>
                                <div class="gold">金钱 <span id="gold-value">${formatGold(match.goldEarned)}</span></div>
                                <div class="date">时间: ${match.gameCreationDate.toLocaleString()}</div>
                            </div>
                            <div class="items">
                                ${match.items.map((item, index) => `
                                    <article id="item${index}">
                                        <img src="items/${item}.png" alt="${index + 1}"> <!-- 使用 'items' 文件夹 -->
                                    </article>
                                `).join('')}
                            </div>
                        </div>
                    </div>
                `;

                gameInfoContainer.style.background = match.win ? 'linear-gradient(135deg, #e0f7fa, #80deea)' : 'linear-gradient(135deg, #f8d7da, #f5c6cb)';
                gameInfoContainer.dataset.gameId = match.gameId;
                matchHistoryList.appendChild(gameInfoContainer);
            });
        }

        function formatGold(gold) {
            return gold >= 1000 ? (gold / 1000).toFixed(1) + 'k' : gold;
        }
    } else {
        console.error('URL中未找到puuid');
    }

    const matchHistoryList = document.getElementById('match-history-list');
    
    if (matchHistoryList) {
        matchHistoryList.addEventListener('click', function(event) {
            const listItem = event.target.closest('li'); // 获取最近的 <li> 元素
            if (listItem) {
                const matchDetails = listItem.innerText; // 获取战绩详情
                const gameId = listItem.dataset.gameId; // 获取 gameId
                showMatchDetails(matchDetails, gameId); // 调用函数显示战绩详情和 gameId
            }
        });
    } 

    function showMatchDetails(details, gameId) {
        const detailWindow = document.createElement('div');
        detailWindow.className = 'pupop';

        const leftDiv = document.createElement('div');
        leftDiv.className = 'left';
        const rightDiv = document.createElement('div');
        rightDiv.className = 'right';

        detailWindow.appendChild(leftDiv);
        detailWindow.appendChild(rightDiv);

        const closeButton = document.createElement('button');
        closeButton.innerText = '关闭';
        closeButton.style.marginBottom = '10px';
        closeButton.onclick = function() {
            document.body.removeChild(detailWindow);
        };

        fetch(`http://localhost:8080/Client/getMatchHistoryBygameId?gameId=${gameId}`)
            .then(response => response.json())
            .then(gameData => {
                const playerData = gameData.participantIdentities.map(participant => participant.player);
                detailWindow.innerHTML = `<h2>战绩详情 gameId=${gameId}</h2> `;
                
                gameData.participants.forEach((participant, index) => {
                    const kda = `${participant.stats.kills} / ${participant.stats.deaths} / ${participant.stats.assists}`;
                    const goldEarned = participant.stats.goldEarned;
                    const championId = participant.championId;
                    const spells1 = participant.spell1Id;
                    const spells2 = participant.spell2Id;
                    const win = participant.stats.win;
                    const puuid = playerData;
                    const items = [
                        participant.stats.item0,
                        participant.stats.item1,
                        participant.stats.item2,
                        participant.stats.item3,
                        participant.stats.item4,
                        participant.stats.item5,
                        participant.stats.item6
                    ];

                    const gameInfoDiv = document.createElement('div');
                    gameInfoDiv.className = 'game-info';
                    gameInfoDiv.dataset.puuid = puuid;

                    gameInfoDiv.innerHTML = `
                        <figure id="yxtx">
                            <div class="name">${playerData[index].gameName}</div>
                            <img src="yxtx/${championId}.png" alt="英雄头像" class="avatar"> <!-- 使用 'yxtx' 文件夹 -->
                        </figure>
                        <div class="spells">
                            <div id="spells1">
                                <img src="jineng/${spells1}.png" alt="召唤师技能1"> <!-- 使用 'jineng' 文件夹 -->
                            </div>
                            <div id="spells2">
                                <img src="jineng/${spells2}.png" alt="召唤师技能2"> <!-- 使用 'jineng' 文件夹 -->
                            </div>
                        </div>
                        <div class="details">
                            <div class="stats">
                                <div class="kda">KDA: <span id="kda-value">${kda}</span></div>
                                <div class="gold">金钱 <span id="gold-value">${formatGold(goldEarned)}</span></div>
                            </div>
                            <div class="items">
                                ${items.map((itemId, itemIndex) => `
                                    <article id="item${itemIndex}">
                                        <img src="items/${itemId}.png" alt="${itemIndex + 1}"> <!-- 使用 'items' 文件夹 -->
                                    </article>
                                `).join('')}
                            </div>
                        </div>
                    `;

                    gameInfoDiv.addEventListener('click', function() {
                        const puuid = playerData[index].puuid;
                        console.log('PUUID:', puuid);
                        window.open(`select.html?puuid=${puuid}`, '_blank');
                    });

                    if (!win) {
                        gameInfoDiv.style.backgroundColor = '#d44d35ad';
                        let leftDiv = detailWindow.querySelector('.left');
                        if (leftDiv === null) {
                            leftDiv = document.createElement('div');
                            leftDiv.className = 'left';
                            detailWindow.appendChild(leftDiv);
                        }
                        leftDiv.appendChild(gameInfoDiv);
                    } else {
                        gameInfoDiv.style.backgroundColor = '#34B3FF';
                        let rightDiv = detailWindow.querySelector('.right');
                        if (rightDiv === null) {
                            rightDiv = document.createElement('div');
                            rightDiv.className = 'right';
                            detailWindow.appendChild(rightDiv);
                        }
                        rightDiv.appendChild(gameInfoDiv);
                    }
                });

                detailWindow.prepend(closeButton);
                detailWindow.style.opacity = 1;
                detailWindow.style.visibility = 'visible';
                document.body.appendChild(detailWindow);
            })
            .catch(error => {
                console.error('通过gameId获取比赛历史时出错:', error);
            });
    }

    function fetchSkillAndItems(id, element) {
        fetch(`http://localhost:8080/Client/jineng?id=${id}`)
            .then(response => response.json())
            .then(data => {
                const image = element.querySelector('img');
                image.src = data.url;
            })
            .catch(error => {
                console.error(`获取技能时出错 ${id}:`, error);
            });
    }

    function fetchWithErrorHandling(url, onSuccess, onError) {
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(onSuccess)
            .catch(onError);
    }
});
