# Hanoi_Java
Hanoi,GUI

# 玩法

选择多色等,通过键盘ASD进行碟子移动,或者通过程序实现

键盘操作,对于3个柱子即ASD柱,将A柱碟子移动到D柱,先按A再按D

# 河内塔解法练习

对1色,2色,3色河内塔进行求解

将答案填入:/src/per/chy/hanoi/ctrl/HanoiAnswer.java,实现Answer 接口 重写work解题流程

# HanoiAnswer.java说明

gv.moveDisk(int src,int dst)表示从src柱移动碟子到dst柱,通过本函数进行解题
