TEX=$(wildcard *.tex)
STY=$(wildcard *.sty)

TARGET=proyecto.pdf

all:$(TARGET)

$(TARGET): $(TEX) $(STY)
	pdflatex proyecto.tex

%.pdf: %.tex
	pdflatex $<


clean:
	$(RM) $(TARGET)

vclean: clean
	$(RM) *.log
	$(RM) *.aux
	$(RM) *.idx
	$(RM) *.toc
	$(RM) *~