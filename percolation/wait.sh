while inotifywait -e close_write ./*.go; do
	clear
	go build && ./percolation
done