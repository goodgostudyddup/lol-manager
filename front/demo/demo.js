document.addEventListener('DOMContentLoaded', function() {
    // 获取比赛历史数据
    fetch("http://localhost:8080/Client/getMatchHistoryBygameId?gameId=9426084690")
        .then(response => response.json())
        .then(gameData => {
            console.log("Game Data Loaded:", gameData); // 调试信息

            // 获取游戏名称和标语
            const playerData = gameData.participantIdentities.map(participant => participant.player);
            // 更新页面标题
            document.querySelector('.name').textContent = gameData.gameId;
            // 获取所有参与者的数据
            gameData.participants.forEach((participant, index) => {
                console.log("Processing participant:", participant); // 调试信息
                const kda = `${participant.stats.kills} / ${participant.stats.deaths} / ${participant.stats.assists}`;
                const goldEarned = participant.stats.goldEarned;
                const championId = participant.championId;
                const spells1 = participant.spell1Id;
                const spells2 = participant.spell2Id;
                const win = participant.stats.win;
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
                
                // 根据 win 字段设置背景颜色
                if (!win) {
                    gameInfoDiv.style.backgroundColor = '#d44d35ad'; // 背景颜色为红色#d44d35ad
                }

                gameInfoDiv.innerHTML = `
                    <figure id="yxtx">
                        <div class="name">${playerData[index].summonerName}</div>
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
                            <div class="kda">KDA: <span id="kda-value">${kda}</span></div>
                            <div class="gold">金钱 <span id="gold-value">${formatGold(goldEarned)}</span></div>
                        </div>
                        <div class="items">
                            ${items.map((itemId, itemIndex) => `
                                <article id="item${itemIndex}">
                                    <img src="" alt="物品${itemIndex + 1}">
                                </article>
                            `).join('')}
                        </div>
                    </div>
                `;

                // 将前五个玩家添加到左侧容器，后五个添加到右侧容器
                if (index < 5) {
                    document.getElementById('left-container').appendChild(gameInfoDiv);
                } else {
                    document.getElementById('right-container').appendChild(gameInfoDiv);
                }

                // 更新英雄头像和技能图标
                fetch(`http://localhost:8080/Client/getChampionIcons?championId=${championId}`)
                    .then(response => response.json())
                    .then(data => {
                        const image = gameInfoDiv.querySelector('.avatar');
                        image.src = data.url;
                    })
                    .catch(error => {
                        console.error('Error fetching champion icon:', error);
                    });

                fetchSkillAndItems(spells1, gameInfoDiv.querySelector('#spells1'));
                fetchSkillAndItems(spells2, gameInfoDiv.querySelector('#spells2'));

                items.forEach((itemId, itemIndex) => {
                    fetch(`http://localhost:8080/Client/getitem?id=${itemId}`)
                        .then(response => response.json())
                        .then(data => {
                            const image = gameInfoDiv.querySelector(`#item${itemIndex} img`);
                            image.src = data.url;
                        })
                        .catch(error => {
                            console.error(`Error fetching item ${itemIndex}:`, error);
                        });
                });
            });
        })
        .catch(error => {
            console.error('Error fetching match history:', error);
        });

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

    function formatGold(gold) {
        return gold >= 1000 ? (gold / 1000).toFixed(1) + 'k' : gold;
    }
});