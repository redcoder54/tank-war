# 坦克大战
FC经典游戏-坦克大战。
为了增加趣味性，允许玩家更改一些游戏设定，比如说坦克速度、开火策略（发射的炮弹不一样）等。

## 游戏按键

- 上: 方向键上
- 下: 方向键下
- 左: 方向键左
- 右: 方向键右
- 开火: 空格键
- 暂停/恢复: Enter


## 自定义配置

配置文件示例
```text
# 敌方坦克数量
initialTankCount=8

# 玩家坦克速度
playerTankSpeed=20
# 敌方坦克速度
enemyTankSpeed=6

# 敌我的坦克开火策略
playerFireStrategy=redcoder.tank.fire.EmojiFireStrategy
enemyFireStrategy=redcoder.tank.fire.BulletFireStrategy

# 敌方坦克生产策略
tankProducer=redcoder.tank.tankproducer.DefaultTankProducer

# 添加自定义的游戏对象碰撞器，多个值用英文逗号分割
#customCollider=redcoder.tank.collider.BulletTankCollider

# 添加自定义的游戏关卡生成器，多个值用英文逗号分割
#customStageGenerator=redcoder.tank.stage.generator.Stage1Generator
```
你可以自由地更改游戏设定，也可以添加自定义的游戏对象碰撞器和游戏关卡生成器。