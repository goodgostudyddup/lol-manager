document.addEventListener('DOMContentLoaded', function() {
    // 向指定的URL发起请求
    fetch('http://localhost:8080/Client/login')
        .then(response => response.json())
        .then(data => {
            if (data === "") {
                alert('请先登录英雄联盟客户端');
            } else {
                console.log('用户已登录:', data);
                const puuid = data.puuid;
                localStorage.setItem('puuid', puuid); // Store puuid in localStorage
                document.getElementById('display-name').textContent = `${data.gameName}#${data.tagLine}`;
                document.getElementById('profile-pic').src = `https://ddragon.leagueoflegends.com/cdn/11.1.1/img/profileicon/${data.profileIconId}.png`;
                document.getElementById('region').textContent = `艾欧尼亚 | ${data.summonerLevel}级`;
                fetch('http://localhost:8080/Client/getToken')
                    .then(tokenResponse => tokenResponse.json())
                    .then(tokenData => {
                        document.getElementById('token').textContent = tokenData.token;
                        console.log(tokenData.token)
                    })
                    .catch(error => {
                        console.error('Error fetching token:', error);
                        document.getElementById('token').textContent = 'Error loading token';
                    });
                fetch('http://localhost:8080/Client/getrank')
                    .then(rankResponse => rankResponse.json())
                    .then(rankData => {
                        const highestDivision = rankData.highestPreviousSeasonEndDivision;
                        const highestTier = rankData.highestPreviousSeasonEndTier;
                        document.getElementById('previous-rank').textContent = `上个赛季最高段位: ${highestDivision} ${highestTier}`;
                    })
                    .catch(rankError => {
                        console.error('Error fetching rank data:', rankError);
                    });

            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});