document.addEventListener('DOMContentLoaded', function() {
    // 从 URL 中获取 puuid
    const urlParams = new URLSearchParams(window.location.search);
    const puuid = urlParams.get('puuid');

    if (puuid) {
        // 使用 puuid 查询详细数据
        fetch(`http://localhost:8080/Client/getInfoByPuuId?puuid=${puuid}`)
            .then(response => response.json())
            .then(data => {
                // 在页面上显示详细数据
                document.getElementById('display-name').textContent = `${data.gameName}#${data.tagLine}`;
                document.getElementById('profile-pic').src = `https://ddragon.leagueoflegends.com/cdn/11.1.1/img/profileicon/${data.profileIconId}.png`;
                document.getElementById('region').textContent = `艾欧尼亚 | ${data.summonerLevel}级`;
                // 其他数据处理...
            })
            .catch(error => {
                console.error('Error fetching user details:', error);
            });
    } else {
        console.error('No puuid found in URL');
    }
});