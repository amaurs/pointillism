setwd("~/Java/pointillism/target/classes")
x <- read.csv(file='dataset.csv')
solution<-kmeans(x,5)
write.table(solution$centers,file="centers.csv",col.names=F,row.names=F)

