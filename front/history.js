document.addEventListener('DOMContentLoaded', function() {
    let matchData = [];
    let totalKills = 0;
    let totalAssists = 0;
    let totalDeaths = 0;
    const puuid = localStorage.getItem('puuid'); // Retrieve puuid from localStorage
    const championUsage = {}; // Object to track champion usage

    // Fetch and display the initial 10 matches
    for (let i = 0; i < 10; i++) {
        fetch(`http://localhost:8080/Client/getProductsMatchHistoryBybegIndex?puuid=${puuid}&begIndex=${i}`) // Add puuid to the API call
            .then(response => response.json())
            .then(data => {
                const gameId = data.games.games[0].gameId;
                const game = data.games.games[0];
                const participant = game.participants[0];
                totalKills += participant.stats.kills;
                totalAssists += participant.stats.assists;
                totalDeaths += participant.stats.deaths;

                const championId = participant.championId;
                championUsage[championId] = (championUsage[championId] || 0) + 1; // Track champion usage

                matchData.push({
                    gameId: gameId,
                    kda: `${participant.stats.kills}/${participant.stats.deaths}/${participant.stats.assists}`,
                    goldEarned: participant.stats.goldEarned,
                    win: participant.stats.win,
                    championId: championId,
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

                // Sort the matchData array by gameCreationDate in descending order
                matchData.sort((a, b) => b.gameCreationDate - a.gameCreationDate);

                if (matchData.length === i + 1) {
                    const kdaRatio = totalDeaths === 0 ? 'Perfect' : (((totalKills + totalAssists) / totalDeaths) * 3).toFixed(2);
                    document.querySelector('.avgkda p').textContent = kdaRatio;
                    displayMatches(matchData);
                    displayTopChampions(championUsage); // Display top champions
                }
            })
            .catch(error => {
                console.error('Error fetching match history:', error);
            });
    }


    function displayMatches(matches) {
        const matchHistoryList = document.querySelector('#match-history-list');
        matchHistoryList.innerHTML = ''; // Clear existing matches
        matches.forEach(match => {
            const gameInfoContainer = document.createElement('li');
            gameInfoContainer.className = 'game-info-container';
            gameInfoContainer.innerHTML = `
                <div class="game-info">
                    <figure id="yxtx">
                        <img src="" alt="英雄头像" class="avatar">
                    </figure>
                    <div class="spells">
                        <div id="spells1">
                            <img src="" alt="召唤师技能1">
                        </div>
                        <div id="spells2">
                            <img src="" alt="召唤师技能2">
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
                                    <img src="" alt="${index + 1}">
                                </article>
                            `).join('')}
                        </div>
                    </div>
                </div>
            `;

            gameInfoContainer.style.background = match.win ? 'linear-gradient(135deg, #e0f7fa, #80deea)' : 'linear-gradient(135deg, #f8d7da, #f5c6cb)';
            gameInfoContainer.dataset.gameId = match.gameId;
            matchHistoryList.appendChild(gameInfoContainer);

            // 更新英雄头像的获取方式为使用 'yxtx' 文件夹
            const championImage = gameInfoContainer.querySelector('.avatar');
            championImage.src = `yxtx/${match.championId}.png`; // 更新为使用 'yxtx' 文件夹

            // 更新技能图标的获取方式为使用 'jineng' 文件夹
            const spells1Image = gameInfoContainer.querySelector('#spells1 img');
            spells1Image.src = `jineng/${match.spells1}.png`; // 更新为使用 'jineng' 文件夹

            const spells2Image = gameInfoContainer.querySelector('#spells2 img');
            spells2Image.src = `jineng/${match.spells2}.png`; // 更新为使用 'jineng' 文件夹

            match.items.forEach((item, index) => {
                const imgElement = gameInfoContainer.querySelector(`#item${index} img`);
                imgElement.src = `items/${item}.png`; // 更新为使用 'items' 文件夹
            });
        });
    }

    function formatGold(gold) {
        return gold >= 1000 ? (gold / 1000).toFixed(1) + 'k' : gold;
    }

    // 添加事件监听器以处理点击事件
    const matchHistoryList = document.getElementById('match-history-list');
    
    if (matchHistoryList) {
        matchHistoryList.addEventListener('click', function(event) {
            const listItem = event.target.closest('li'); // 获取最近的 <li> 元素
            if (listItem) {
                const matchDetails = listItem.innerText; // 获取战绩详情
                const gameId = listItem.dataset.gameId; // 获取 gameId
                // showPopup()
                showMatchDetails(matchDetails, gameId); // 调用函数显示战绩详情和 gameId
            }
        });
    } 

    // 新增函数以显示战绩详情
    function showMatchDetails(details, gameId) {
        // 创建一个新的 div 窗口
        const detailWindow = document.createElement('div');
        detailWindow.className='pupop';

        
        const leftDiv = document.createElement('div');
        leftDiv.className='left';
        const  rightDiv = document.createElement('div');
        rightDiv.className='right';

        detailWindow.appendChild(leftDiv)
        detailWindow.appendChild(rightDiv)

        // 添加关闭按钮
        const closeButton = document.createElement('button');
        closeButton.innerText = '关闭';
        closeButton.style.marginBottom = '10px';
        closeButton.onclick = function() {
            document.body.removeChild(detailWindow); // 关闭窗口
        };

        // 在窗口中显示比赛数据
        fetch(`http://localhost:8080/Client/getMatchHistoryBygameId?gameId=${gameId}`)
            .then(response => response.json())
            .then(gameData => {
                // console.log("Game Data Loaded:", gameData); // 调试信息

                // 获取游戏名称和标语
                const playerData = gameData.participantIdentities.map(participant => participant.player);
                detailWindow.innerHTML = `<h2>战绩详情 gameId=${gameId}</h2> `;
                
                // 获取所有参与者的数据
                gameData.participants.forEach((participant, index) => {
                    const kda = `${participant.stats.kills} / ${participant.stats.deaths} / ${participant.stats.assists}`;
                    const goldEarned = participant.stats.goldEarned;
                    const championId = participant.championId;
                    const spells1 = participant.spell1Id;
                    const spells2 = participant.spell2Id;
                    const win = participant.stats.win;
                    const puuid=playerData;
                    const items = [
                        participant.stats.item0,
                        participant.stats.item1,
                        participant.stats.item2,
                        participant.stats.item3,
                        participant.stats.item4,
                        participant.stats.item5,
                        participant.stats.item6
                    ];


                    // 创建游戏信息的 HTML 结构
                    const gameInfoDiv = document.createElement('div');
                    gameInfoDiv.className = 'game-info';

                    // 添加 puuid 到 gameInfoDiv 的数据集
                    gameInfoDiv.dataset.puuid = puuid;

                    gameInfoDiv.innerHTML = `
                        <figure id="yxtx">
                            
                            <img src="" alt="英雄头像" class="avatar">
                        </figure>
                        <div class="spells">
                            <div id="spells1">
                                <img src="" alt="召唤师技能1">
                            </div>
                            <div id="spells2">
                                <img src="" alt="召唤师技能2">
                            </div>
                        </div>
                        <div class="details">
                            <div class="stats">
                            <div class="name">${playerData[index].gameName}</div>
                                <div class="kda">KDA: <span id="kda-value">${kda}</span></div>
                                <div class="gold">金钱 <span id="gold-value">${formatGold(goldEarned)}</span></div>
                            </div>
                            <div class="items">
                                ${items.map((itemId, itemIndex) => `
                                    <article id="item${itemIndex}">
                                        <img src="" alt="${itemIndex + 1}">
                                    </article>
                                `).join('')}
                            </div>
                        </div>
                    `;

                    // 添加点击事件监听器当点击的时候带着puuid跳转到select页面
                    gameInfoDiv.addEventListener('click', function() {
                        const puuid = playerData[index].puuid;
                        console.log('PUUID:', puuid);
                        // Open select page in a new tab with puuid as a query parameter
                        window.open(`select.html?puuid=${puuid}`, '_blank');
                    });

                    // 根据 win 字段设置背景颜色
                    if (!win) {
                            gameInfoDiv.style.backgroundColor = '#d44d35ad'; // 背景颜色为红色
                            let leftDiv = detailWindow.querySelector('.left')
                            if(leftDiv === null) {
                                leftDiv = document.createElement('div');
                                leftDiv.className='left';
                                detailWindow.appendChild(leftDiv)
                            }
                            leftDiv.appendChild(gameInfoDiv)
                        } else {
                            gameInfoDiv.style.backgroundColor = '#34B3FF';
                            let rightDiv = detailWindow.querySelector('.right')
                            if(rightDiv === null) {
                                rightDiv = document.createElement('div');
                                rightDiv.className='right';
                                detailWindow.appendChild(rightDiv)
                            }
                            rightDiv.appendChild(gameInfoDiv)
                        }

                    // 更新英雄头像的获取方式为使用 'yxtx' 文件夹
                    const championImage = gameInfoDiv.querySelector('.avatar');
                    championImage.src = `yxtx/${participant.championId}.png`; // 确保使用 championId

                    // 更新技能图标的获取方式为使用 'jineng' 文件夹
                    const spells1Image = gameInfoDiv.querySelector('#spells1 img');
                    spells1Image.src = `jineng/${participant.spell1Id}.png`; // 更新为使用 'jineng' 文件夹

                    const spells2Image = gameInfoDiv.querySelector('#spells2 img');
                    spells2Image.src = `jineng/${participant.spell2Id}.png`; // 更新为使用 'jineng' 文件夹

                    items.forEach((itemId, itemIndex) => {
                        const image = gameInfoDiv.querySelector(`#item${itemIndex} img`);
                        image.src = `items/${itemId}.png`; // 更新为使用 'items' 文件夹
                    });
                });

                // 将关闭按钮添加到窗口
                detailWindow.prepend(closeButton); // 将关闭按钮放在顶部
                detailWindow.style.opacity = 1;
                detailWindow.style.visibility = 'visible'
                // 将窗口添加到 body
                document.body.appendChild(detailWindow);
            })
            .catch(error => {
                console.error('Error fetching match history by gameId:', error);
            });
    }

    function fetchSkillAndItems(id, element) {
        fetch(`http://localhost:8080/Client/getSummonerSkill?id=${id}`)
            .then(response => response.json())
            .then(data => {
                const image = element.querySelector('img');
                image.src = data.url;
            })
            .catch(error => {
                console.error(`Error fetching skill for ${id}:`, error);
            });
    }

    function showPopup() {
        const popup = document.querySelector('.pupop');
        popup.style.opacity = '1';
        popup.style.visibility = 'visible';
    }

    function hidePopup() {
        const popup = document.querySelector('.pupop');
        popup.style.opacity = '0';
        popup.style.visibility = 'hidden';
    }

    // // 绑定关闭按钮事件
    document.querySelector('.pupop button').addEventListener('click', hidePopup);

    // Improved error handling for fetch requests
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

    // Example usage of fetchWithErrorHandling
    fetchWithErrorHandling(
        `http://localhost:8080/Client/getChampionIcons?championId=${championId}`,
        data => {
            const image = gameInfoDiv.querySelector('.avatar');
            image.src = data.url;
        },
        error => {
            console.error('Error fetching champion icon:', error);
        }
    );

    
});


