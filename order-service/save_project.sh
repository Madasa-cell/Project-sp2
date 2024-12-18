#!/bin/bash

# Directory to traverse
SOURCE_DIR="."  # "." means current directory

# Output file
OUTPUT_FILE="project-contents.txt"

# Clear the output file if it already exists
> "$OUTPUT_FILE"

# Traverse files and append content
while IFS= read -r -d '' file; do
    # Only process text-based files
    if file "$file" | grep -q 'text'; then
        echo "======== File: $file ========" >> "$OUTPUT_FILE"
        cat "$file" >> "$OUTPUT_FILE"
        echo -e "\n" >> "$OUTPUT_FILE"
    fi
done < <(find "$SOURCE_DIR" -type f -print0)

echo "All content saved to $OUTPUT_FILE"
