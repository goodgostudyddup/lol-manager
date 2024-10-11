import tkinter as tk
from tkinter import messagebox
import requests
import os
import psutil  # 用于检查进程

# 替换为你的Riot Games API密钥
API_KEY = 'YOUR_RIOT_GAMES_API_KEY'
REGION = 'na1'  # 替换为你所在的服务器区域

def get_summoner_data(summoner_name):
    url = f'https://{REGION}.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summoner_name}?api_key={API_KEY}'
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        return None

def get_match_history(account_id):
    url = f'https://{REGION}.api.riotgames.com/lol/match/v4/matchlists/by-account/{account_id}?api_key={API_KEY}'
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        return None

def search_summoner():
    summoner_name = entry.get()
    summoner_data = get_summoner_data(summoner_name)
    if summoner_data:
        account_id = summoner_data['accountId']
        match_history = get_match_history(account_id)
        if match_history:
            matches = match_history['matches']
            match_info = '\n'.join([f"Game ID: {match['gameId']}, Champion: {match['champion']}" for match in matches[:10]])
            messagebox.showinfo("Match History", match_info)
        else:
            messagebox.showerror("Error", "Could not retrieve match history.")
    else:
        messagebox.showerror("Error", "Summoner not found.")

def check_lol_client_running():
    # 检查进程列表中是否有英雄联盟客户端
    for proc in psutil.process_iter(['name']):
        if proc.info['name'] == 'LeagueClient.exe':
            messagebox.showinfo("检查结果", "英雄联盟客户端正在运行。")
            return
    messagebox.showerror("检查结果", "英雄联盟客户端未运行。")

# 创建主窗口
root = tk.Tk()
root.title("英雄联盟战绩查询")

# 创建输入框和按钮
tk.Label(root, text="召唤师名称:").pack(pady=5)
entry = tk.Entry(root)
entry.pack(pady=5)
tk.Button(root, text="查询", command=search_summoner).pack(pady=5)
tk.Button(root, text="检查客户端是否运行", command=check_lol_client_running).pack(pady=5)

# 运行主循环
root.mainloop()
