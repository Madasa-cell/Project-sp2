#!/bin/bash

# Define the root directory to search
ROOT_DIR="."

# Define the output file
OUTPUT_FILE="all_files_content.txt"

# Clear the output file if it already exists
> "$OUTPUT_FILE"

# Find all files and append their content to the output file
find "$ROOT_DIR" -type f | while read -r file; do
    echo "----- FILE: $file -----" >> "$OUTPUT_FILE"
    cat "$file" >> "$OUTPUT_FILE"
    echo -e "\n" >> "$OUTPUT_FILE"
done

echo "All file content saved to $OUTPUT_FILE"