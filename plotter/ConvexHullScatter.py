import matplotlib.pyplot as plt
import numpy

vertices = numpy.genfromtxt("vertices.csv", delimiter=",")
print("vertices.csv")
print(vertices.shape)
#print(vertices)

edges = numpy.genfromtxt("edges.csv", delimiter=",")
print("edges.csv")
print(edges.shape)
#print(edges)

for i in range(0,vertices.shape[0]):
	x = vertices[i,0]
	y = vertices[i,1]
	#print(x,y)
	plt.plot(x,y,'ro')

for i in range(0,edges.shape[0]):
	ax = edges[i,0]
	ay = edges[i,1]
	bx = edges[i,2]
	by = edges[i,3]
	#print(ax,ay,bx,by)
	plt.plot([ax,bx],[ay,by],linestyle='-',marker='o')

plt.show()