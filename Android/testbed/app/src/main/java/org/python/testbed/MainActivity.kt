from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle
from reportlab.lib import colors

def generate_report(mix, test_results, ai_recommendation):
    doc = SimpleDocTemplate("Mix_Report.pdf", pagesize=letter)
    story = []

    # Title
    story.append(Paragraph("EPDM/HTPB Mix Report", styles["Heading1"]))
    story.append(Spacer(1, 12))

    # Mix Formula
    story.append(Paragraph("Mix Formula (PHR):", styles["Heading2"]))
    data = [
        ["Material", "PHR"],
        ["EPDM", mix["EPDM"]],
        ["HTPB", mix["HTPB"]],
        ["Kevlar", mix["Kevlar"]],
        ["Carbon Black", mix["CarbonBlack"]],
    ]
    table = Table(data)
    table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.grey),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
    ]))
    story.append(table)
    story.append(Spacer(1, 12))

    # Test Results
    story.append(Paragraph("Test Results:", styles["Heading2"]))
    data = [
        ["Property", "Value"],
        ["Erosion Rate", f"{test_results['erosion_rate']} mm/s"],
        ["Tensile Strength", f"{test_results['tensile_strength']} MPa"],
        ["Elongation", f"{test_results['elongation']}%"],
    ]
    table = Table(data)
    story.append(table)
    story.append(Spacer(1, 12))

    # AI Recommendation
    story.append(Paragraph("AI Recommendation:", styles["Heading2"]))
    story.append(Paragraph(ai_recommendation))

    doc.build(story)
    print("Report generated: Mix_Report.pdf")

# Example usage
mix = {"EPDM": 90, "HTPB": 10, "Kevlar": 12, "CarbonBlack": 25}
test_results = {"erosion_rate": 0.06, "tensile_strength": 10.2, "elongation": 78}
ai_recommendation = "Increase ATH by 2 PHR for better ablation resistance."
generate_report(mix, test_results, ai_recommendation)
